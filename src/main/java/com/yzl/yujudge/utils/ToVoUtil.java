package com.yzl.yujudge.utils;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.yzl.yujudge.model.JudgeSolutionEntity;
import com.yzl.yujudge.vo.SolutionVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuzhanglong
 * @description 转换为vo对象的工具类
 * @date 2020-7-22 11:45
 */
public class ToVoUtil {
    /**
     * @param solutionEntityList  solution实体类列表
     * @author yuzhanglong
     * @description 将solution实体类列表转为solution的视图层对象
     * @date 2020-7-22 11:47
     */
    public static List<SolutionVO> solutionsEntityListToSolutionVoList(List<JudgeSolutionEntity> solutionEntityList){
        List<SolutionVO> solutions = new ArrayList<>();
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        solutionEntityList.forEach(res -> {
            SolutionVO s = mapper.map(res, SolutionVO.class);
            solutions.add(s);
        });
        return solutions;
    }
}
