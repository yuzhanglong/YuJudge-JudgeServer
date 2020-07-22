package com.yzl.yujudge.repository;

import com.yzl.yujudge.model.JudgeSolutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author yuzhanglong
 * @date 2020-7-20 19:54:22
 */
public interface SolutionRepository extends JpaRepository<JudgeSolutionEntity, Long> {
    /**
     * 获取所有的解决方案
     *
     * @return JudgeSolutionEntity 解决方案实体类
     * @param pkProblemId 所属的problemId
     * @author yuzhanglong
     * @date 2020-7-22 23:06
     * @description 传入problemId,获取这个problems下所有的解决方案
     */
    List<JudgeSolutionEntity> findAllByPkProblem(Long pkProblemId);
}
