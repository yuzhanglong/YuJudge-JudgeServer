package com.yzl.yujudge.repository;

import com.yzl.yujudge.model.JudgeProblemEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author yuzhanglong
 * @date 2020-7-18 04:47:16
 * @description problem相关的查询类接口
 */
public interface ProblemRepository extends JpaRepository<JudgeProblemEntity, Long> {
    /**
     * 通过题目Id从数据库中查询题目信息
     *
     * @param id 需要查询问题的id
     * @return JudgeProblemEntity 题目信息实体类
     * @author yuzhanglong
     * @description 通过题目Id从数据库中查询题目信息
     * @date 2020-7-18 04:47:16
     */
    JudgeProblemEntity findOneById(Long id);


    /**
     * 对问题基于限制数量的查询，以创建时间排序
     *
     * @param pageable 分页参数
     * @return List JudgeProblemEntity 多个题目信息实体类
     * @author yuzhanglong
     * @description 获取最近创建的10个问题
     * @date 2020-08-06 21:01:06
     */
    List<JudgeProblemEntity> findByOrderByCreateTimeDesc(Pageable pageable);
}