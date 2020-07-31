package com.yzl.yujudge.utils;


import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.yzl.yujudge.dto.JudgeHostJudgeRequestDTO;
import com.yzl.yujudge.dto.SolutionDTO;
import com.yzl.yujudge.model.JudgeProblemEntity;
import com.yzl.yujudge.model.JudgeSolutionEntity;
import com.yzl.yujudge.model.SubmissionEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuzhanglong
 * @description 转换为vo对象的工具类
 * @date 2020-7-30 09:59
 */
public class ToDtoUtil {

    /**
     * @author yuzhanglong
     * @description 将某个submission的实体对象以及其对应的problem实体对象转换成JudgeHostDTO
     * @date 2020-7-30 09:59
     */
    public static JudgeHostJudgeRequestDTO submissionToJudgeHostDTO(JudgeProblemEntity judgeProblemEntity, SubmissionEntity submissionEntity) {
        JudgeHostJudgeRequestDTO judgeHostDTO = new JudgeHostJudgeRequestDTO();
        judgeHostDTO.setMemoryLimit(judgeProblemEntity.getMemoryLimit());
        judgeHostDTO.setLanguage(submissionEntity.getLanguage());
        judgeHostDTO.setSubmissionCode(submissionEntity.getCodeContent());
        judgeHostDTO.setOutputLimit(judgeProblemEntity.getOutputLimit());
        judgeHostDTO.setJudgePreference(submissionEntity.getJudgePreference());
        judgeHostDTO.setCpuTimeLimit(judgeProblemEntity.getTimeLimit());
        judgeHostDTO.setRealTimeLimit(judgeProblemEntity.getTimeLimit());
        // 获取解决方案
        List<JudgeSolutionEntity> expectedSolutions = judgeProblemEntity.getJudgeSolutionEntityList();
        List<SolutionDTO> solutions = solutionEntityListToSolutionDtoList(expectedSolutions);
        judgeHostDTO.setSolutions(solutions);
        return judgeHostDTO;
    }

    /**
     * @author yuzhanglong
     * @description 解决方案的实体对象转化为数据传输对象
     * @date 2020-7-30 10:02
     */
    public static List<SolutionDTO> solutionEntityListToSolutionDtoList(List<JudgeSolutionEntity> judgeSolutionEntities) {
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        List<SolutionDTO> solutionDTOList = new ArrayList<>();
        judgeSolutionEntities.forEach(solution -> {
            solutionDTOList.add(mapper.map(solution, SolutionDTO.class));
        });
        return solutionDTOList;
    }
}
