package com.yzl.yujudge.repository;


import com.yzl.yujudge.model.ProblemSetEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

/**
 * @author yuzhanglong
 * @description 题目集查询对象
 * @date 2020-08-08 21:37:25
 */
public interface ProblemSetRepository extends JpaRepository<ProblemSetEntity, Long> {
    /**
     * 分页获取题目集合
     *
     * @param pageable 分页配置
     * @param name     关键字
     * @return ProblemSetEntity 的分页对象
     * @author yuzhanglong
     * @date 2020-08-08 21:40:03
     * @description 分页获取题目集合
     */
    @Query("select p from ProblemSetEntity p " +
            "where p.name like %?1% " +
            "order by p.createTime desc")
    Page<ProblemSetEntity> findByName(String name, Pageable pageable);

    /**
     * 分页获取题目集合
     *
     * @param currentTime 当前时间
     * @param pageable    分页对象
     * @param name        名称
     * @return ProblemSetEntity 的分页对象
     * @author yuzhanglong
     * @date 2020-08-09 11:26:49
     * @description 分页获取题目集合
     * 我们额外传入一个当前时间
     * 用来筛选当前时间介于开始时间和截止时间之间的题目
     */

    @Query("select p from ProblemSetEntity p " +
            "where p.startTime <= ?1 " +
            "and p.deadline >= ?1 " +
            "and p.name like %?2%" +
            "order by p.createTime desc")
    Page<ProblemSetEntity> findByNameBetweenCurrentTime(Date currentTime, String name, Pageable pageable);

    /**
     * 根据ID 获取题目集
     *
     * @param problemSetId 题目集id
     * @return ProblemSetEntity 实体对象
     * @author yuzhanglong
     * @date 2020-08-09 11:51:58
     * @description 根据ID 获取题目集
     */
    ProblemSetEntity findOneById(Long problemSetId);
}