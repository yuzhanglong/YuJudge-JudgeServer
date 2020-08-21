package com.yzl.yujudge.repository;

import com.yzl.yujudge.model.JudgeSolutionEntity;

import java.util.List;

/**
 * @author yuzhanglong
 * @date 2020-7-20 19:54:22
 */
public interface SolutionRepository extends SoftDeleteRepository<JudgeSolutionEntity> {
    /**
     * 传入problemId,获取这个problems下所有的解决方案
     *
     * @param pkProblemId 所属的problemId
     * @return JudgeSolutionEntity 解决方案实体类
     * @author yuzhanglong
     * @date 2020-7-22 23:06
     */
    List<JudgeSolutionEntity> findAllByPkProblem(Long pkProblemId);


    /**
     * 获取单个解决方案
     *
     * @param solutionId 解决方案id
     * @return JudgeSolutionEntity 解决方案实体类
     * @author yuzhanglong
     * @date 2020-7-22 23:06
     */
    JudgeSolutionEntity findOneById(Long solutionId);
}
