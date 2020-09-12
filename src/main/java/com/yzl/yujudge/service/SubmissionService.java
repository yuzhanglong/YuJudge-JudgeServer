package com.yzl.yujudge.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yzl.yujudge.bo.JudgeHostBO;
import com.yzl.yujudge.core.authorization.UserHolder;
import com.yzl.yujudge.core.configuration.SubmissionExecutorConfiguration;
import com.yzl.yujudge.core.enumeration.JudgeConditionEnum;
import com.yzl.yujudge.core.enumeration.JudgeResultEnum;
import com.yzl.yujudge.core.enumeration.ProblemSetConditionEnum;
import com.yzl.yujudge.core.exception.http.ForbiddenException;
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
import com.yzl.yujudge.store.redis.SubmissionCache;
import com.yzl.yujudge.utils.DateTimeUtil;
import com.yzl.yujudge.utils.JudgeResultCalculateUtil;
import com.yzl.yujudge.utils.ToDtoUtil;
import com.yzl.yujudge.utils.ToEntityUtil;
import com.yzl.yujudge.vo.SubmissionThreadPoolVO;
import org.springframework.beans.factory.annotation.Value;
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
    private final SubmissionCache submissionCache;
    private final UserGroupService userGroupService;
    private final SubmissionExecutorConfiguration submissionExecutorConfiguration;
    private final ProblemSetService problemSetService;
    public static final int MAX_SUBMISSION_TRY_AMOUNT = 5;
    public static final int MAX_SUBMISSION_COUNT_DATE = 40;

    @Value("${submission.frequency}")
    private Long submissionFrequency;

    public SubmissionService(
            SubmissionRepository submissionRepository,
            ProblemRepository problemRepository,
            ProblemSetRepository problemSetRepository,
            UserRepository userRepository,
            JudgeHostService judgeHostService,
            JudgeHostRepository judgeHostRepository,
            SubmissionCache submissionCache,
            SubmissionExecutorConfiguration submissionExecutorConfiguration,
            UserGroupService userGroupService,
            ProblemSetService problemSetService) {
        this.submissionRepository = submissionRepository;
        this.problemRepository = problemRepository;
        this.problemSetRepository = problemSetRepository;
        this.userRepository = userRepository;
        this.judgeHostService = judgeHostService;
        this.judgeHostRepository = judgeHostRepository;
        this.submissionCache = submissionCache;
        this.submissionExecutorConfiguration = submissionExecutorConfiguration;
        this.userGroupService = userGroupService;
        this.problemSetService = problemSetService;
    }

    /**
     * 初始化用户的提交，包括二次验证、数据库写入
     *
     * @param submissionDTO 提交相关的数据传输对象
     * @return SubmissionEntity 的实体对象，以供后续操作
     * @author yuzhanglong
     * @date 2020-7-29 13:40:54
     */
    public SubmissionEntity initSubmissionWithProblemSet(SubmissionDTO submissionDTO) {
        Long userId = UserHolder.getUserId();
        validateUserSubmissionFrequency(userId);
        // 查询题目集是否存在
        ProblemSetEntity problemSetEntity = problemSetRepository.findOneById(submissionDTO.getProblemSetId());
        if (problemSetEntity == null) {
            throw new NotFoundException("B0011");
        }
        // 是否超时？如果超时，我们不执行
        if (problemSetService.getProblemSetCondition(problemSetEntity) != ProblemSetConditionEnum.RUNNING) {
            throw new NotFoundException("B0021");
        }
        UserEntity userEntity = userRepository.findOneById(userId);

        // 将这个用户添加到题目集参与者中
        if (!isUserInProblemSet(problemSetEntity.getId(), userEntity)) {
            problemSetEntity.getParticipants().add(userEntity);
        }

        // 验证目标problem是否存在
        JudgeProblemEntity judgeProblemEntity = problemRepository.findOneById(submissionDTO.getProblemId());

        // 如果目标problem不存在
        if (judgeProblemEntity == null) {
            throw new NotFoundException("B0002");
        }

        SubmissionEntity submissionEntity = ToEntityUtil.submissionDtoToSubmissionEntity(submissionDTO, judgeProblemEntity);
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
     * 初始化用户的提交，包括二次验证、数据库写入, 此方法的提交不和题目集绑定
     * <p>
     * 你可以理解为公共题目
     *
     * @param submissionDTO 提交相关的数据传输对象
     * @return SubmissionEntity 的实体对象，以供后续操作
     * @author yuzhanglong
     * @date 2020-8-31 00:09:09
     */
    public SubmissionEntity initSubmissionWithoutProblemSet(SubmissionDTO submissionDTO) {
        Long userId = UserHolder.getUserId();
        validateUserSubmissionFrequency(userId);
        UserEntity userEntity = userRepository.findOneById(userId);
        JudgeProblemEntity judgeProblemEntity = problemRepository.findOneById(submissionDTO.getProblemId());
        // 如果目标problem不存在
        if (judgeProblemEntity == null) {
            throw new NotFoundException("B0002");
        }
        SubmissionEntity submissionEntity = ToEntityUtil.submissionDtoToSubmissionEntity(submissionDTO, judgeProblemEntity);
        // 我们将本次提交设为waiting（等待判题）
        submissionEntity.setJudgePreference(submissionDTO.getJudgePreference());
        submissionEntity.setCondition(JudgeConditionEnum.WAITING.toString());
        submissionEntity.setCreateTime(new Date());
        submissionEntity.setCodeContent(submissionDTO.getCodeContent());
        submissionEntity.setCreator(userEntity);
        // 执行保存
        submissionRepository.save(submissionEntity);
        return submissionEntity;
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
        JudgeProblemEntity judgeProblemEntity = submissionEntity.getProblem();

        // 进入这个方法说明已经完成了排队操作，我们将状态置为【PENDING -- 判题中】
        SubmissionEntity submission = setSubmissionPendingCondition(submissionEntity);
        JudgeHostJudgeRequestDTO judgeHostDTO = ToDtoUtil.submissionToJudgeHostDTO(judgeProblemEntity, submission);

        // 选择一个合适的服务器
        JudgeHostBO judgeHostToRequest = judgeHostService.chooseJudgeHostToRequest();
        if (judgeHostToRequest == null) {
            saveJudgeResult(submission, "无判题服务器工作", false);
            return;
        }

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
        JudgeProblemEntity problemEntity = problemRepository.findOneById(problemId);
        if (problemEntity == null) {
            throw new NotFoundException("B0002");
        }
        Pageable pageable = PageRequest.of(start, size);
        return submissionRepository.findAllByProblemOrderByCreateTimeDesc(problemEntity, pageable);
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

        // 系统默认的题目、题目集无限制用户组，不受时间，用户限制
        if (userGroupService.isUserProblemSetFree()) {
            return submissionEntity;
        }

        // 不是自己的提交
        if (!UserHolder.getUserId().equals(submissionEntity.getCreator().getId())) {
            submissionEntity.setCodeContent("抱歉, 代码不予显示");
            submissionEntity.getJudgeResult().setExtraInfo(new ArrayList<>());
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

    /**
     * 序列化提交数据
     * 【数组单个元素示例】
     * {
     * "submissionAmount": 1,
     * "hour": 0,
     * "time": 1597680000000
     * }
     *
     * @param results 查询结果
     * @return 处理后的结果列表，内容见@description
     * @author yuzhanglong
     * @date 2020-8-20 10:54:52
     * @see SubmissionRepository#countSubmissionGroupByHoursByJudgeHostId(Date, Date, Long)
     */
    public static List<Map<String, Object>> publishSubmissionHourlyCount(Set<List<Object>> results) {
        List<Map<String, Object>> items = new ArrayList<>();
        for (List<Object> result : results) {
            Map<String, Object> itemForOneHour = new HashMap<>(12);
            itemForOneHour.put("time", result.get(0));
            itemForOneHour.put("hour", result.get(1));
            itemForOneHour.put("submissionAmount", result.get(2));
            items.add(itemForOneHour);
        }
        return items;
    }

    /**
     * 获取判题服务器线程池配置信息
     *
     * @return 配置内容视图层对象
     * @author yuzhanglong
     * @date 2020-9-7 13:21:21
     */
    public SubmissionThreadPoolVO getSubmissionThreadPoolConfig() {
        Integer maxPoolSize = submissionExecutorConfiguration.submissionAsyncServiceExecutor().getMaximumPoolSize();
        Integer workingAmount = submissionExecutorConfiguration.submissionAsyncServiceExecutor().getActiveCount();
        Integer maxQueueAmount = SubmissionExecutorConfiguration.BLOCKING_QUEUE_CAPACITY;
        return new SubmissionThreadPoolVO(maxPoolSize, workingAmount, maxQueueAmount);
    }

    /**
     * 设置判题服务器同时连接数目
     *
     * @param size 大小
     * @author yuzhanglong
     * @date 2020-9-7 13:21:21
     */
    public void setSubmissionThreadPoolMaxSize(Integer size) {
        submissionExecutorConfiguration.submissionAsyncServiceExecutor().setCorePoolSize(size);
        submissionExecutorConfiguration.submissionAsyncServiceExecutor().setMaximumPoolSize(size);
    }

    /**
     * 修改提交状态
     *
     * @param condition 提交状态
     * @author yuzhanglong
     * @date 2020-9-8 22:25:50
     */
    public void changeSubmissionCondition(Long submissionId, String condition) {
        SubmissionEntity submissionEntity = submissionRepository.findOneById(submissionId);
        if (submissionEntity == null) {
            throw new NotFoundException("B0005");
        }
        JudgeResultEnum judgeResultEnum = JudgeResultEnum.toJudgeResult(condition);
        // 传入了错误的状态
        if (judgeResultEnum == null) {
            throw new NotFoundException("A0012");
        }
        submissionEntity.setCondition(condition);
        submissionRepository.save(submissionEntity);
    }

    /**
     * 设置提交频率。单位为秒
     *
     * @param frequency 提交频率。单位为秒
     * @author yuzhanglong
     * @date 2020-9-12 15:51:30
     */
    public void setSubmissionFrequency(Long frequency) {
        submissionFrequency = frequency;
    }

    /**
     * 获取提交频率。单位为秒
     *
     * @return 提交频率。单位为秒
     * @author yuzhanglong
     * @date 2020-9-12 15:51:30
     */
    public Long getSubmissionFrequency() {
        return submissionFrequency;
    }

    /**
     * 检测用户是否频繁提交
     * 本质上利用了redis键的过期机制，用户执行一次提交，redis记录，并设置过期时间
     * 如果用户在规定的间隔时间内再次提交，我们可以在redis中查到这个值
     *
     * @return 是否频繁提交
     * @author yuzhanglong
     * @date 2020-9-12 16:02:19
     */
    private Boolean isUserSubmissionFrequently(Long uid) {
        Object data = submissionCache.getUserFromSubmissionNote(uid);
        return data != null;
    }

    /**
     * 用户提交间隔验证 / 记录保存
     * 如果 submissionFrequency 为 0 不验证
     *
     * @author yuzhanglong
     * @date 2020-9-12 16:09:39
     */
    private void validateUserSubmissionFrequency(Long userId) {
        // 没有设限，不验证
        if (submissionFrequency == 0) {
            return;
        }
        // 提交频繁性检测
        if (isUserSubmissionFrequently(userId)) {
            throw new ForbiddenException("A0013");
        }
        submissionCache.setUserSubmissionNote(userId, submissionFrequency);
    }
}