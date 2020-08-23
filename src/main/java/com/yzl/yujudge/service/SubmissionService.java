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
import com.yzl.yujudge.utils.DateTimeUtil;
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
 * 处理提交相关的业务逻辑
 *
 * @author yuzhanglong
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
    public static final int MAX_SUBMISSION_TRY_AMOUNT = 5;
    public static final int MAX_SUBMISSION_COUNT_DATE = 40;

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
     * 初始化用户的提交，包括二次验证、数据库写入
     *
     * @param submissionDTO 提交相关的数据传输对象
     * @return SubmissionEntity 的实体对象，以供后续操作
     * @author yuzhanglong
     * @date 2020-7-29 13:40:54
     */
    public SubmissionEntity initSubmission(SubmissionDTO submissionDTO) {
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
     * 业务层面的提交相关验证
     *
     * @param submissionDTO 提交相关数据传输对象
     * @author yuzhanglong
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
     * 将submission添加到任务队列中，并执行判题
     * 注意: 如果一次判题失败，我们会进行多次尝试，这个尝试的次数由 MAX_SUBMISSION_TRY_AMOUNT 决定
     *
     * @param submissionEntity 本次提交对应的实体类
     * @param tryAmount        尝试次数
     * @author yuzhanglong
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
        JudgeHostJudgeRequest judgeHostJudgeRequest = new JudgeHostJudgeRequest(
                judgeHostToRequest.getBaseUrl(),
                judgeHostToRequest.getCondition().getPort()
        );
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
     * 将submission 状态置为【PENDING -- 判题中】
     *
     * @param submissionEntity 提交的实体对象
     * @return SubmissionEntity 更新后的实体对象
     * @author yuzhanglong
     * @date 2020-7-29 19:45:32
     */
    private SubmissionEntity setSubmissionPendingCondition(SubmissionEntity submissionEntity) {
        submissionEntity.setCondition(JudgeConditionEnum.PENDING.toString());
        submissionRepository.save(submissionEntity);
        return submissionEntity;
    }

    /**
     * 保存判题服务器的判题结果
     *
     * @param submissionEntity  提交的实体对象
     * @param judgeResultString 判题结果的json字符串
     * @param isJudgeSuccess    是否成功完成判题
     * @author yuzhanglong
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
     * 获取某个problem下的用户提交(分页)
     *
     * @param start     开始的条目
     * @param problemId 目标问题id
     * @param size      条目数量
     * @return 提交实体的分页对象
     * @author yuzhanglong
     * @date 2020-7-31 19:56:19
     */
    public Page<SubmissionEntity> getSubmissionByProblemId(Integer start, Integer size, Long problemId) {
        Pageable pageable = PageRequest.of(start, size);
        return submissionRepository.findAllByPkProblemOrderByCreateTimeDesc(problemId, pageable);
    }

    /**
     * 获取某个submission的详细信息，如果找不到，我们会抛出异常
     *
     * @param submissionId 某次提交的id
     * @return SubmissionEntity 找到的submission的实体对象
     * @author yuzhanglong
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
     * 获取某用户最近的提交, 包括每一天的ac个数等资料
     *
     * @param userId 需要查询的用户Id
     * @param end    结束时间
     * @param begin  开始时间
     * @return 每一天的提交情况，详见@description
     * @author yuzhanglong
     * @date 2020-08-07 12:19:37
     */
    public List<Map<String, Object>> countUserRecentSubmission(Long userId, Date begin, Date end) {
        // 统计所有提交
        Date b = DateTimeUtil.removeTimeFromDateObject(begin);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(b);
        c2.setTime(b);
        c2.add(Calendar.DAY_OF_MONTH, 1);
        List<Map<String, Object>> results = new ArrayList<>();
        int cnt = 0;
        Date publishedEnd = DateTimeUtil.removeTimeFromDateObject(end);
        // 按天进行时间推移、并记录数据 同时限制了天数
        while (!c1.getTime().equals(publishedEnd) || cnt > MAX_SUBMISSION_COUNT_DATE) {
            Long acceptAmount = submissionRepository.getUserSubmissionTotalAmountByJudgeResult(userId, c1.getTime(), c2.getTime(), JudgeResultEnum.ACCEPT.name());
            Long totalAmount = submissionRepository.getUserSubmissionTotalAmount(userId, c1.getTime(), c2.getTime());
            Map<String, Object> m = new HashMap<>(4);
            m.put("acceptAmount", acceptAmount);
            m.put("totalAmount", totalAmount);
            m.put("time", c1.getTime());
            results.add(m);
            // 推移时间
            c2.add(Calendar.DAY_OF_MONTH, 1);
            c1.add(Calendar.DAY_OF_MONTH, 1);
            cnt++;
        }
        return results;
    }


    /**
     * 判断用户是否在/参与了某个题目集
     *
     * @param problemSetId 题目集id
     * @param userEntity   用户实体对象
     * @return Boolean 用户是否在题目集中
     * @author yuzhanglong
     * @date 2020-08-12 21:33:53
     */
    private Boolean isUserInProblemSet(Long problemSetId, UserEntity userEntity) {
        Long entity = problemSetRepository.countByIdAndParticipants(problemSetId, userEntity);
        return entity > 0;
    }

    /**
     * 获取用户判题结果的相关信息，例如wa数目、ac数目、tle数目等
     *
     * @author yuzhanglong
     * @date 2020-8-21 00:43:23
     */
    public List<Map<String, Object>> countUserJudgeResult(Long userId) {
        Set<List<Object>> counts = submissionRepository.countUserTotalSubmissionCountDate(userId);
        List<Map<String, Object>> res = new ArrayList<>();
        for (List<Object> count : counts) {
            Map<String, Object> tmp = new HashMap<>(5);
            tmp.put("type", count.get(0));
            tmp.put("amount", count.get(1));
            res.add(tmp);
        }
        return res;
    }
}