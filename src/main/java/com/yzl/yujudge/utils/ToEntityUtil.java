package com.yzl.yujudge.utils;

import com.yzl.yujudge.dto.ProblemDTO;
import com.yzl.yujudge.dto.RegisterDTO;
import com.yzl.yujudge.dto.SolutionDTO;
import com.yzl.yujudge.dto.SubmissionDTO;
import com.yzl.yujudge.model.JudgeProblemEntity;
import com.yzl.yujudge.model.JudgeSolutionEntity;
import com.yzl.yujudge.model.SubmissionEntity;
import com.yzl.yujudge.model.UserEntity;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuzhanglong
 * @description 转换为entity实体的工具类
 * @date 2020-7-20 23:45:42
 */
public class ToEntityUtil {
    /**
     * @author yuzhanglong
     * @description 由problemDto转换为problem
     * @date 2020-7-20 23:45:42
     */
    public static JudgeProblemEntity problemDtoToProblemEntity(ProblemDTO problemDTO) {
        JudgeProblemEntity problem = new JudgeProblemEntity();
        BeanUtils.copyProperties(problemDTO, problem);
        return problem;
    }

    /**
     * @param solutionDTO 解决方案的数据传输对象
     * @author yuzhanglong
     * @description 由solutionDTO转换为solution
     * @date 2020-7-29 13:27:36
     */
    public static JudgeSolutionEntity solutionDtoToSolutionEntity(SolutionDTO solutionDTO) {
        JudgeSolutionEntity s = new JudgeSolutionEntity();
        BeanUtils.copyProperties(solutionDTO, s);
        return s;
    }

    /**
     * @author yuzhanglong
     * @description 由solutionDTO转换为solution
     * @date 2020-7-29 13:27:46
     */
    public static List<JudgeSolutionEntity> solutionDtoToSolutionEntityList(List<SolutionDTO> solutionDtoList) {
        List<JudgeSolutionEntity> solutions = new ArrayList<>();
        solutionDtoList.forEach(res -> {
            JudgeSolutionEntity s = solutionDtoToSolutionEntity(res);
            solutions.add(s);
        });
        return solutions;
    }

    /**
     * @param submissionDTO 提交相关的数据传输对象
     * @author yuzhanglong
     * @description 由solutionDTO转换为solution
     * @date 2020-7-29 13:28:04
     */
    public static SubmissionEntity submissionDtoToSubmissionEntity(SubmissionDTO submissionDTO, JudgeProblemEntity problemEntity) {
        SubmissionEntity submissionEntity = new SubmissionEntity();
        submissionEntity.setProblem(problemEntity);
        submissionEntity.setCodeContent(submissionDTO.getCodeContent());
        submissionEntity.setLanguage(submissionDTO.getLanguage());
        return submissionEntity;
    }

    public static UserEntity registerDtoToUserEntity(RegisterDTO registerDTO) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(registerDTO, userEntity);
        return userEntity;
    }
}
