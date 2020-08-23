package com.yzl.yujudge.repository;

import com.yzl.yujudge.model.JudgeHostEntity;


/**
 * 判题服务器相关的查询类接口
 *
 * @author yuzhanglong
 * @date 2020-7-30 18:56
 */
public interface JudgeHostRepository extends BaseRepository<JudgeHostEntity> {
    /**
     * 根据ID 获取判题机信息
     *
     * @param judgeHostId 判题机id
     * @return JudgeHostEntity 实体对象
     * @author yuzhanglong
     * @date 2020-8-18 23:54:01
     */
    JudgeHostEntity findOneById(Long judgeHostId);

    /**
     * 根据IP地址 获取判题机信息
     *
     * @param baseUrl base地址
     * @return JudgeHostEntity 实体对象
     * @author yuzhanglong
     * @date 2020-8-21 22:51:40
     */
    JudgeHostEntity findOneByBaseUrl(String baseUrl);
}
