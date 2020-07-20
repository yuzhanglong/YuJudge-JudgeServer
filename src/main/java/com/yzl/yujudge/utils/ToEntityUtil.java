package com.yzl.yujudge.utils;

import com.yzl.yujudge.dto.ProblemDTO;
import com.yzl.yujudge.dto.SolutionDTO;
import com.yzl.yujudge.model.JudgeProblemEntity;
import com.yzl.yujudge.model.JudgeSolutionEntity;
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

    public static JudgeSolutionEntity solutionDtoToSolutionEntity(SolutionDTO solutionDTO) {
        JudgeSolutionEntity s = new JudgeSolutionEntity();
        BeanUtils.copyProperties(solutionDTO, s);
        return s;
    }

    public static List<JudgeSolutionEntity> solutionDtoToSolutionEntityList(List<SolutionDTO> solutionDtoList) {
        List<JudgeSolutionEntity> solutions = new ArrayList<>();
        solutionDtoList.forEach(res -> {
            JudgeSolutionEntity s = solutionDtoToSolutionEntity(res);
            solutions.add(s);
        });
        return solutions;
    }
}
