package com.yzl.yujudge.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yzl.yujudge.core.enumeration.JudgeConditionEnum;
import com.yzl.yujudge.core.exception.http.NotFoundException;
import com.yzl.yujudge.dto.JudgeHostJudgeRequestDTO;
import com.yzl.yujudge.dto.JudgeResultDTO;
import com.yzl.yujudge.dto.SubmissionDTO;
import com.yzl.yujudge.model.JudgeProblemEntity;
import com.yzl.yujudge.model.SubmissionEntity;
import com.yzl.yujudge.network.JudgeHostJudgeRequest;
import com.yzl.yujudge.repository.ProblemRepository;
import com.yzl.yujudge.repository.SubmissionRepository;
import com.yzl.yujudge.utils.JudgeResultCalculateUtil;
import com.yzl.yujudge.utils.ToDtoUtil;
import com.yzl.yujudge.utils.ToEntityUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Date;
import java.util.List;

/**
 * @author yuzhanglong
 * @description 处理提交相关的业务逻辑
 * @date 2020-7-29 00:32:32
 */

@Service
public class SubmissionService {
    private final SubmissionRepository submissionRepository;
    private final ProblemRepository problemRepository;

    public SubmissionService(SubmissionRepository submissionRepository, ProblemRepository problemRepository) {
        this.submissionRepository = submissionRepository;
        this.problemRepository = problemRepository;
    }

    /**
     * @return SubmissionEntity 的实体对象，以供后续操作
     * @author yuzhanglong
     * @description 初始化用户的提交，包括二次验证、数据库写入
     * @date 2020-7-29 13:40:54
     */

    public SubmissionEntity initSubmission(SubmissionDTO submissionDTO) {
        validateSubmission(submissionDTO);
        SubmissionEntity submissionEntity = ToEntityUtil.submissionDtoToSubmissionEntity(submissionDTO);
        // 我们将本次提交设为waiting（等待判题）
        submissionEntity.setCondition(JudgeConditionEnum.WAITING.toString());
        submissionEntity.setJudgePreference(submissionDTO.getJudgePreference());
        submissionEntity.setCodeContent(submissionDTO.getCodeContent());
        submissionEntity.setCreateTime(new Date());
        submissionRepository.save(submissionEntity);
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

        // 验证语言是否在允许的范围内
        List<String> language = judgeProblemEntity.getAllowedLanguage();
        boolean isLanguageAccept = language.contains(submissionDTO.getLanguage());
        if (!isLanguageAccept) {
            throw new NotFoundException("B0004");
        }
    }

    /**
     * @author yuzhanglong
     * @description 将submission添加到任务队列中
     * @date 2020-7-29 14:21:59
     */
    @Async(value = "submissionAsyncServiceExecutor")
    public void addSubmissionTask(SubmissionEntity submissionEntity) {
        // 获取这个submission对应的problem实体
        JudgeProblemEntity judgeProblemEntity = problemRepository.findOneById(submissionEntity.getPkProblem());

        // TODO: 如果目标problem不存在,需要记录错误（不是返回异常，因为这是异步操作）
        // TODO: 对于某段时间内大量的提交，有一定的并发量，我们直接在数据库save有些不妥，需要使用缓存

        // 进入这个方法说明已经完成了排队操作，我们将状态置为【PENDING -- 判题中】
        SubmissionEntity submission = setSubmissionPendingCondition(submissionEntity);
        JudgeHostJudgeRequestDTO judgeHostDTO = ToDtoUtil.submissionToJudgeHostDTO(judgeProblemEntity, submission);

        // 执行判题请求
        JudgeHostJudgeRequest judgeHostJudgeRequest = new JudgeHostJudgeRequest();
        try {
            String res = judgeHostJudgeRequest.judgeSubmission(judgeHostDTO);
            System.out.println(res);
            // 保存判题结果
            saveJudgeResult(submission, res);
        } catch (WebClientResponseException e) {
            System.out.println(e.getResponseBodyAsString());
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
     * @param submissionEntity 提交的实体对象
     * @author yuzhanglong
     * @description 保存判题服务器的判题结果
     * @date 2020-7-30 10:34
     */
    private void saveJudgeResult(SubmissionEntity submissionEntity, String judgeResultJsonString) {
        submissionEntity.setJudgeResult(judgeResultJsonString);
        submissionEntity.setCondition(JudgeConditionEnum.SUCCESS.toString());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JudgeResultDTO judgeResult = objectMapper.readValue(judgeResultJsonString, JudgeResultDTO.class);
            JudgeResultCalculateUtil calculator = new JudgeResultCalculateUtil(judgeResult);
            calculator.executeCalculate();
            submissionEntity.setTimeCost(Long.valueOf(calculator.getTimeCost()));
            submissionEntity.setMemoryCost(Long.valueOf(calculator.getMemoryCost()));
            submissionEntity.setCondition(calculator.countJudgeResult().toString());
        } catch (JsonProcessingException e) {
            submissionEntity.setCondition(JudgeConditionEnum.ERROR.toString());
        }
        submissionRepository.save(submissionEntity);
    }

    /**
     * @param start 开始的条目
     * @param problemId  目标问题id
     * @param size 条目数量
     * @author yuzhanglong
     * @return Page<SubmissionEntity> 提交实体的分页对象
     * @description 获取某个problem下的用户提交(分页)
     * @date 2020-7-31 19:56:19
     */
    public Page<SubmissionEntity> getSubmissionByProblemId(Integer start, Integer size, Long problemId){
        Pageable pageable = PageRequest.of(start, size);
        return submissionRepository.findAllByPkProblemOrderByCreateTimeDesc(problemId, pageable);
    }
}