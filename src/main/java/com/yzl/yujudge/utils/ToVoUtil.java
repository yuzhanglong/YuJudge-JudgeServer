package com.yzl.yujudge.utils;

import com.github.dozermapper.core.DozerBeanMapper;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.yzl.yujudge.model.JudgeHostEntity;
import com.yzl.yujudge.model.JudgeSolutionEntity;
import com.yzl.yujudge.model.SubmissionEntity;
import com.yzl.yujudge.vo.JudgeHostVO;
import com.yzl.yujudge.vo.SolutionVO;
import com.yzl.yujudge.vo.SubmissionDetailVO;
import com.yzl.yujudge.vo.SubmissionVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuzhanglong
 * @description 转换为vo对象的工具类
 * @date 2020-7-22 11:45
 */
public class ToVoUtil {
    /**
     * @param solutionEntityList solution实体类列表
     * @author yuzhanglong
     * @description 将solution实体类列表转为solution的视图层对象
     * @date 2020-7-22 11:47
     */
    public static List<SolutionVO> solutionsEntityListToSolutionVoList(List<JudgeSolutionEntity> solutionEntityList) {
        List<SolutionVO> solutions = new ArrayList<>();
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        solutionEntityList.forEach(res -> {
            SolutionVO s = mapper.map(res, SolutionVO.class);
            solutions.add(s);
        });
        return solutions;
    }

    /**
     * @param judgeHostEntityList judgeHost实体类列表
     * @author yuzhanglong
     * @description 将judgeHost实体类列表转为judgeHost的视图层对象
     * @date 2020-7-30 19:18
     */
    public static List<JudgeHostVO> judgeHostEntityListToJudgeHostVoList(List<JudgeHostEntity> judgeHostEntityList) {
        List<JudgeHostVO> judgeHostVOList = new ArrayList<>();
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        judgeHostEntityList.forEach(res -> {
            JudgeHostVO judgeHostVO = mapper.map(res, JudgeHostVO.class);
            judgeHostVOList.add(judgeHostVO);
        });
        return judgeHostVOList;
    }

    /**
     * @param submissionEntity submission的实体类
     * @author yuzhanglong
     * @description 将submission的实体类列表转为submissionDetail的实体类对象
     * @date 2020-8-1 12:05:06
     */
    public static SubmissionDetailVO submissionEntityToSubmissionDetailVO(SubmissionEntity submissionEntity) {
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        return mapper.map(submissionEntity, SubmissionDetailVO.class);
    }
}
