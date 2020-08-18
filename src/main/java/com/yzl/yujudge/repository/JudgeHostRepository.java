package com.yzl.yujudge.repository;

import com.yzl.yujudge.model.JudgeHostEntity;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author yuzhanglong
 * @description 判题服务器相关的查询类接口
 * @date 2020-7-30 18:56
 */
public interface JudgeHostRepository extends JpaRepository<JudgeHostEntity, Long> {
    /**
     * 根据ID 获取判题机信息
     *
     * @param judgeHostId 判题机id
     * @return ProblemSetEntity 实体对象
     * @author yuzhanglong
     * @date 2020-8-18 23:54:01
     * @description 根据ID 获取题目集
     */
    JudgeHostEntity findOneById(Long judgeHostId);
}
