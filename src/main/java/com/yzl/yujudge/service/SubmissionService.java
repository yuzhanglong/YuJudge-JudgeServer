package com.yzl.yujudge.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yzl.yujudge.bo.JudgeHostBO;
import com.yzl.yujudge.core.authorization.UserHolder;
import com.yzl.yujudge.core.enumeration.JudgeConditionEnum;
import com.yzl.yujudge.core.enumeration.JudgeResultEnum;
import com.yzl.yujudge.core.exception.http.NotFoundException;
import com.yzl.yujudge.dto.JudgeHostJudgeRequestDTO;
import com.yzl.yujudge.dto.JudgeResultDTO;
import com.yzl.yujudge.dto.SubmissionDTO;
import com.yzl.yujudge.model.JudgeProblemEntity;
import com.yzl.yujudge.model.ProblemSetEntity;
import com.yzl.yujudge.model.SubmissionEntity;
import com.yzl.yujudge.model.UserEntity;
import com.yzl.yujudge.network.JudgeHostJudgeRequest;
import com.yzl.yujudge.repository.*;
import com.yzl.yujudge.utils.JudgeResultCalculateUtil;
import com.yzl.yujudge.utils.ToDtoUtil;
import com.yzl.yujudge.utils.ToEntityUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.*;

/**
 * @author yuzhanglong
 * @description 处理提交相关的业务逻辑
 * @date 2020-7-29 00:32:32
 */

@Service
public class SubmissionService {
    private final SubmissionRepository submissionRepository;
    private final ProblemRepository problemRepository;
    private final ProblemSetRepository problemSetRepository;
    private final UserRepository userRepository;
    private final JudgeHostService judgeHostService;
    private final JudgeHostRepository judgeHostRepository;
    private static final int COUNT_USER_RECENT_SUBMISSION_MAX_DAYS = 20;
    public static final String AC_AMOUNT_KEY_NAME = "acAmount";
    public static final String TOTAL_AMOUNT_KEY_NAME = "totalAmount";
    public static final int MAX_SUBMISSION_TRY_AMOUNT = 5;

    public SubmissionService(
            SubmissionRepository submissionRepository,
            ProblemRepository problemRepository,
            ProblemSetRepository problemSetRepository,
            UserRepository userRepository,
            JudgeHostService judgeHostService,
            JudgeHostRepository judgeHostRepository) {
        this.submissionRepository = submissionRepository;
        this.problemRepository = problemRepository;
        this.problemSetRepository = problemSetRepository;
        this.userRepository = userRepository;
        this.judgeHostService = judgeHostService;
        this.judgeHostRepository = judgeHostRepository;
    }

    /**
     * @param submissionDTO 提交相关的数据传输对象
     * @return SubmissionEntity 的实体对象，以供后续操作
     * @author yuzhanglong
     * @description 初始化用户的提交，包括二次验证、数据库写入
     * @date 2020-7-29 13:40:54
     */
    public SubmissionEntity initSubmission(SubmissionDTO submissionDTO) {
        // TODO: 不要对数据库直接操作, 可以考虑放入线程池异步操作或者使用缓存?

        Long userId = UserHolder.getUserId();
        ProblemSetEntity problemSetEntity = problemSetRepository.findOneById(submissionDTO.getProblemSetId());
        if (problemSetEntity == null) {
            throw new NotFoundException("B0011");
        }
        UserEntity userEntity = userRepository.findOneById(userId);
        if (!isUserInProblemSet(problemSetEntity.getId(), userEntity)) {
            problemSetEntity.getParticipants().add(userEntity);
        }
        validateSubmission(submissionDTO);
        SubmissionEntity submissionEntity = ToEntityUtil.submissionDtoToSubmissionEntity(submissionDTO);
        // 我们将本次提交设为waiting（等待判题）
        submissionEntity.setCondition(JudgeConditionEnum.WAITING.toString());
        submissionEntity.setJudgePreference(submissionDTO.getJudgePreference());
        submissionEntity.setCodeContent(submissionDTO.getCodeContent());
        submissionEntity.setCreateTime(new Date());
        submissionEntity.setCreator(userEntity);
        submissionEntity.setProblemSet(problemSetEntity);
        // 执行保存
        submissionRepository.save(submissionEntity);
        problemSetRepository.save(problemSetEntity);
        return submissionEntity;
    }

    /**
     * @author yuzhanglong
     * @description 业务层面的提交相关验证
     * @date 2020-7-29 14:21:59
     */
    private void validateSubmission(SubmissionDTO submissionDTO) {
        // 验证目标problem是否存在
        JudgeProblemEntity judgeProblemEntity = problemRepository.findOneById(submissionDTO.getProblemId());

        // 如果目标problem不存在
        if (judgeProblemEntity == null) {
            throw new NotFoundException("B0002");
        }
    }

    /**
     * @param submissionEntity 本次提交对应的实体类
     * @param tryAmount        尝试次数
     * @author yuzhanglong
     * @description 将submission添加到任务队列中
     * @date 2020-7-29 14:21:59
     */
    @Async(value = "submissionAsyncServiceExecutor")
    public void addSubmissionTask(SubmissionEntity submissionEntity, Integer tryAmount) {
        // 获取这个submission对应的problem实体
        JudgeProblemEntity judgeProblemEntity = problemRepository.findOneById(submissionEntity.getPkProblem());

        // 进入这个方法说明已经完成了排队操作，我们将状态置为【PENDING -- 判题中】
        SubmissionEntity submission = setSubmissionPendingCondition(submissionEntity);
        JudgeHostJudgeRequestDTO judgeHostDTO = ToDtoUtil.submissionToJudgeHostDTO(judgeProblemEntity, submission);

        // 选择一个合适的服务器
        JudgeHostBO judgeHostToRequest = judgeHostService.chooseJudgeHostToRequest();

        // 执行判题请求
        JudgeHostJudgeRequest judgeHostJudgeRequest = new JudgeHostJudgeRequest(judgeHostToRequest.getAddress());
        String res;
        try {
            res = judgeHostJudgeRequest.judgeSubmission(judgeHostDTO);
            submissionEntity.setJudgeHost(judgeHostRepository.findOneById(judgeHostToRequest.getId()));
            // 保存判题结果
            saveJudgeResult(submission, res, true);
        } catch (WebClientResponseException e) {
            // 不是正常的200返回会执行下面的代码，本次判题无效，结果将被置为 UNKNOWN_ERROR -- 未知错误
            // 非200返回的原因一般来自出题者，例如限制大小不合规范、没有添加测试点（期望输入、输出）
            res = new String(e.getResponseBodyAsByteArray());
            // 递归，继续尝试判题，知道达到最大尝试次数为止
            if (tryAmount < MAX_SUBMISSION_TRY_AMOUNT) {
                // 没有达到规定的尝试次数，继续尝试
                addSubmissionTask(submissionEntity, tryAmount + 1);
            } else {
                submissionEntity.setJudgeHost(judgeHostRepository.findOneById(judgeHostToRequest.getId()));
                saveJudgeResult(submission, res, false);
            }
        }
    }

    /**
     * @param submissionEntity 提交的实体对象
     * @return SubmissionEntity 更新后的实体对象
     * @author yuzhanglong
     * @description 将submission 状态置为【PENDING -- 判题中】
     * @date 2020-7-29 19:45:32
     */
    private SubmissionEntity setSubmissionPendingCondition(SubmissionEntity submissionEntity) {
        submissionEntity.setCondition(JudgeConditionEnum.PENDING.toString());
        submissionRepository.save(submissionEntity);
        return submissionEntity;
    }

    /**
     * @param submissionEntity  提交的实体对象
     * @param judgeResultString 判题结果的json字符串
     * @param isJudgeSuccess    是否成功完成判题
     * @author yuzhanglong
     * @description 保存判题服务器的判题结果
     * @date 2020-7-30 10:34
     */
    private void saveJudgeResult(SubmissionEntity submissionEntity, String judgeResultString, Boolean isJudgeSuccess) {
        submissionEntity.setCondition(JudgeConditionEnum.SUCCESS.toString());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if (isJudgeSuccess) {
                JudgeResultDTO judgeResult = objectMapper.readValue(judgeResultString, JudgeResultDTO.class);
                JudgeResultCalculateUtil calculator = new JudgeResultCalculateUtil(judgeResult);
                submissionEntity.setJudgeResult(judgeResult);
                calculator.executeCalculate();
                submissionEntity.setTimeCost(Long.valueOf(calculator.getTimeCost()));
                submissionEntity.setMemoryCost(Long.valueOf(calculator.getMemoryCost()));
                submissionEntity.setCondition(calculator.countJudgeResult().toString());
                submissionEntity.setIsAcBefore(isAcBeforeInProblemSet(submissionEntity));
            } else {
                JudgeResultDTO judgeResult = new JudgeResultDTO();
                List<String> res = new ArrayList<>();
                res.add(judgeResultString);
                judgeResult.setExtraInfo(res);
                submissionEntity.setJudgeResult(judgeResult);
                submissionEntity.setCondition(JudgeResultEnum.UNKNOWN_ERROR.toString());
            }
        } catch (JsonProcessingException e) {
            submissionEntity.setCondition(JudgeResultEnum.UNKNOWN_ERROR.toString());
        }
        submissionRepository.save(submissionEntity);
    }

    /**
     * @param start     开始的条目
     * @param problemId 目标问题id
     * @param size      条目数量
     * @return Page<SubmissionEntity> 提交实体的分页对象
     * @author yuzhanglong
     * @description 获取某个problem下的用户提交(分页)
     * @date 2020-7-31 19:56:19
     */
    public Page<SubmissionEntity> getSubmissionByProblemId(Integer start, Integer size, Long problemId) {
        Pageable pageable = PageRequest.of(start, size);
        return submissionRepository.findAllByPkProblemOrderByCreateTimeDesc(problemId, pageable);
    }

    /**
     * @param submissionId 某次提交的id
     * @return SubmissionEntity 找到的submission的实体对象
     * @author yuzhanglong
     * @description 获取某个submission的详细信息，如果找不到，我们会抛出异常
     * @date 2020-8-1 11:51:18
     */
    public SubmissionEntity getSubmissionDataById(Long submissionId) {
        SubmissionEntity submissionEntity = submissionRepository.findOneById(submissionId);
        if (submissionEntity == null) {
            throw new NotFoundException("B0005");
        }
        return submissionEntity;
    }

    /**
     * @param userId 需要查询的用户Id
     * @param days   查询的天数(从当前时间往前推移)
     * @return List<Map < String, Long>> 每一天的提交情况，详见@description
     * @author yuzhanglong
     * @description 获取某用户最近的提交, 包括每一天的ac个数等资料
     * 注意: 天数不得小于20天
     * 返回内容的格式：map数组，单个map包括: 当天的ac数量以及总的提交数量
     * 例如：(单个map)
     * {
     * "acAmount": 2,
     * "totalAmount": 6
     * }
     * @date 2020-08-07 12:19:37
     */
    public List<Map<String, Long>> countUserRecentSubmission(Long userId, Integer days) {
        int finalDay = days > COUNT_USER_RECENT_SUBMISSION_MAX_DAYS ? COUNT_USER_RECENT_SUBMISSION_MAX_DAYS : days;
        List<Map<String, Long>> result = new ArrayList<>(finalDay);

        // TODO:下面的代码可以在sql查询层面上进行优化
        long lastAc = 0;
        long lastTotal = 0;
        for (int i = 1; i <= finalDay; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, i * -1);
            long acAmount = submissionRepository.countAllByPkUserAndJudgeConditionEqualsAndCreateTimeAfter(userId, "ACCEPT", calendar.getTime()) - lastAc;
            long totAmount = submissionRepository.countAllByPkUserAndCreateTimeAfter(userId, calendar.getTime()) - lastTotal;
            Map<String, Long> map = new HashMap<>(3);
            map.put(AC_AMOUNT_KEY_NAME, acAmount);
            map.put(TOTAL_AMOUNT_KEY_NAME, totAmount);
            map.put("time", calendar.getTime().getTime());
            System.out.println(map.get("time"));
            lastAc += acAmount;
            lastTotal += totAmount;
            result.add(map);
        }
        return result;
    }

    /**
     * @param problemSetId 题目集id
     * @param userEntity   用户实体对象
     * @return Boolean 用户是否在题目集中
     * @author yuzhanglong
     * @date 2020-08-12 21:33:53
     * @description 判断用户是否在/参与了某个题目集
     */
    private Boolean isUserInProblemSet(Long problemSetId, UserEntity userEntity) {
        Long entity = problemSetRepository.countByIdAndParticipants(problemSetId, userEntity);
        return entity > 0;
    }

    /**
     * @param submissionEntity 题目集实体对象
     * @return Boolean 用户是否在题目集中
     * @author yuzhanglong
     * @date 2020-08-13 00:52:38
     * @description 判断用户在本题目集中在此之前是否已经ac过了某一道题目
     */
    private Boolean isAcBeforeInProblemSet(SubmissionEntity submissionEntity) {
        Long problemSetId = submissionEntity.getProblemSet().getId();
        Long userId = submissionEntity.getCreator().getId();
        Long problemId = submissionEntity.getPkProblem();
        long acAmount = submissionRepository.getAcAmountByProblemSetIdAndUserIdAndProblemId(problemSetId, userId, problemId);
        return acAmount > 0;
    }
}