package com.yzl.yujudge.repository;

import com.yzl.yujudge.model.SubmissionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * @author yuzhanglong
 * @date 2020-7-29 13:02:18
 * @description 提交相关查询类接口
 */
public interface SubmissionRepository extends JpaRepository<SubmissionEntity, Long> {

    /**
     * 获取单个提交
     *
     * @param id 提交id
     * @return JudgeSolutionEntity 解决方案实体类
     * @author yuzhanglong
     * @date 2020-7-22 23:06
     * @description 获取单个解决方案
     */
    SubmissionEntity findOneById(Long id);


    /**
     * 根据problemId 获取多个提交
     *
     * @param problemId 问题的id
     * @param pageable  分页参数
     * @return SubmissionEntity 解决方案实体类List
     * @author yuzhanglong
     * @date 2020-7-31 19:18:51
     * @description 通过问题id获取多个解决方案, 按时间先后倒序排列
     */
    Page<SubmissionEntity> findAllByPkProblemOrderByCreateTimeDesc(Long problemId, Pageable pageable);


    /**
     * 获取某天之后的所有submission
     *
     * @param time   开始时间
     * @param userId 目标用户Id
     * @return SubmissionEntity 解决方案实体类List
     * @author yuzhanglong
     * @date 2020-08-07 13:09:06
     * @description 获取某天之后的所有submission
     */
    List<SubmissionEntity> findAllByPkUserAndCreateTimeAfter(Long userId, Date time);

    /**
     * 获取某天之后的所有submission的数量（限定状态）
     *
     * @param time      开始时间
     * @param condition 需要查询的状态
     * @param userId    目标用户Id
     * @return Long 数量
     * @author yuzhanglong
     * @date 2020-08-07 13:09:06
     * @description 获取某天之后的所有submission
     */
    Long countAllByPkUserAndJudgeConditionEqualsAndCreateTimeAfter(Long userId, String condition, Date time);


    /**
     * 获取某天之后的所有submission的数量
     *
     * @param time   开始时间
     * @param userId 目标用户Id
     * @return Long 数量
     * @author yuzhanglong
     * @date 2020-08-07 14:57:44
     * @description 获取某天之后的所有submission数量
     */
    Long countAllByPkUserAndCreateTimeAfter(Long userId, Date time);
}
