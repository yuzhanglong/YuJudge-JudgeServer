package com.yzl.yujudge.repository;

import com.yzl.yujudge.model.JudgeProblemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yuzhanglong
 * @date 2020-7-18 04:47:16
 */
public interface ProblemRepository extends JpaRepository<JudgeProblemEntity, Long>{
    /**
     * 通过题目Id从数据库中查询题目信息
     * @author yuzhanglong
     * @param id 需要查询问题的id
     * @return JudgeProblemEntity 题目信息实体类
     * @description 通过题目Id从数据库中查询题目信息
     * @date 2020-7-18 04:47:16
     */
    JudgeProblemEntity findOneById(Long id);
}
