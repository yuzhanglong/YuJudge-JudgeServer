package com.yzl.yujudge.service;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.yzl.yujudge.core.enumerations.JudgeConditionEnum;
import com.yzl.yujudge.core.exception.http.NotFoundException;
import com.yzl.yujudge.dto.JudgeDTO;
import com.yzl.yujudge.dto.SolutionDTO;
import com.yzl.yujudge.dto.SubmissionDTO;
import com.yzl.yujudge.model.JudgeProblemEntity;
import com.yzl.yujudge.model.JudgeSolutionEntity;
import com.yzl.yujudge.model.SubmissionEntity;
import com.yzl.yujudge.network.JudgeRequest;
import com.yzl.yujudge.repository.ProblemRepository;
import com.yzl.yujudge.repository.SubmissionRepository;
import com.yzl.yujudge.utils.ToEntityUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        if (!language.contains(submissionDTO.getLanguage())) {
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
        JudgeProblemEntity judgeProblemEntity = problemRepository.findOneById(submissionEntity.getPkProblem());

        // TODO: 1.如果目标problem不存在,需要记录错误（不是返回异常，因为这是异步操作）
        //  2.对于某段时间内大量的提交，有一定的并发量，我们直接在数据库save有些不妥，可以考虑使用缓存

        // 进入这个方法说明已经完成了排队操作，我们将状态置为【PENDING -- 判题中】
        SubmissionEntity submission = setSubmissionPendingCondition(submissionEntity);
        List<JudgeSolutionEntity> solutionEntities = judgeProblemEntity.getJudgeSolutionEntityList();
        JudgeDTO judgeDTO = new JudgeDTO();

        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        List<SolutionDTO> solutionDTOList = new ArrayList<>();
        solutionDTOList.add(mapper.map(solutionEntities.get(0), SolutionDTO.class));
        judgeDTO.setSolutions(solutionDTOList);
        judgeDTO.setLanguage("JAVA");
        judgeDTO.setSubmissionCode("import java.util.Scanner;\n\npublic class Main {\n    public static void main(String[] args) {\n        Scanner in = new Scanner(System.in);\n        int a = in.nextInt();\n        int b = in.nextInt();\n        System.out.println(a + b);\n    }\n}");
        judgeDTO.setMemoryLimit(65536);
        judgeDTO.setOutputLimit(100000);
        JudgeRequest judgeRequest = new JudgeRequest();
        String res = judgeRequest.judgeSubmission(judgeDTO);
        System.out.println(res);
    }

    /**
     * @param submissionEntity 提交的实体对象
     * @return SubmissionEntity 更新后的实体对象
     * @author yuzhanglong
     * @description 将submission 状态置为【PENDING -- 判题中】
     * @date 2020-7-29 19:45:32
     */
    private SubmissionEntity setSubmissionPendingCondition(SubmissionEntity submissionEntity) {
        SubmissionEntity submission = submissionEntity;
        submission.setCondition(JudgeConditionEnum.PENDING.toString());
        submissionRepository.save(submission);
        return submission;
    }
}
