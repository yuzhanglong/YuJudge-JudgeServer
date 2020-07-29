package com.yzl.yujudge.service;

import com.yzl.yujudge.core.enumerations.JudgeConditionEnum;
import com.yzl.yujudge.core.exception.http.NotFoundException;
import com.yzl.yujudge.dto.SubmissionDTO;
import com.yzl.yujudge.model.JudgeProblemEntity;
import com.yzl.yujudge.model.SubmissionEntity;
import com.yzl.yujudge.repository.ProblemRepository;
import com.yzl.yujudge.repository.SubmissionRepository;
import com.yzl.yujudge.utils.ToEntityUtil;
import org.springframework.stereotype.Service;

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
     * @author yuzhanglong
     * @description 提交用户代码
     * @date 2020-7-29 13:40:54
     */
    public void submitCode(SubmissionDTO submissionDTO) {
        validateSubmission(submissionDTO);
        SubmissionEntity submissionEntity = ToEntityUtil.submissionDtoToSubmissionEntity(submissionDTO);
        // 我们将本次提交设为waiting（等待判题）
        submissionEntity.setCondition(JudgeConditionEnum.WAITING.toString());
        submissionRepository.save(submissionEntity);
        //TODO: 将用户提交加入提交线程池中(异步)
    }

    /**
     * @author yuzhanglong
     * @description 业务层面的提交相关验证
     * @date 2020-7-29 14:21:59
     */
    private void validateSubmission(SubmissionDTO submissionDTO){
        // 验证目标problem是否存在
        JudgeProblemEntity judgeProblemEntity = problemRepository.findOneById(submissionDTO.getProblemId());

        // 如果目标problem不存在
        if (judgeProblemEntity == null) {
            throw new NotFoundException("B0002");
        }

        // 验证语言是否在允许的范围内
        List<String> language = judgeProblemEntity.getAllowedLanguage();
        if(!language.contains(submissionDTO.getLanguage())){
            throw new NotFoundException("B0004");
        }
    }
}
