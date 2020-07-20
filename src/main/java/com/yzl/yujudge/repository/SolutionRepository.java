package com.yzl.yujudge.repository;

import com.yzl.yujudge.model.JudgeSolutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yuzhanglong
 * @date 2020-7-20 19:54:22
 */
public interface SolutionRepository extends JpaRepository<JudgeSolutionEntity, Long> {

}
