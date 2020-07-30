package com.yzl.yujudge.repository;

import com.yzl.yujudge.model.JudgeHostEntity;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author yuzhanglong
 * @description 判题服务器相关的查询类接口
 * @date 2020-7-30 18:56
 */
public interface JudgeHostRepository extends JpaRepository<JudgeHostEntity, Long> {

}
