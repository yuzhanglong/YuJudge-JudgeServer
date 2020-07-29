package com.yzl.yujudge.repository;

import com.yzl.yujudge.model.SubmissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

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
}
