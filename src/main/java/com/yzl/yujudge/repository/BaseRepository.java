package com.yzl.yujudge.repository;

import com.yzl.yujudge.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 基础查询接口
 *
 * @author yuzhanglong
 * @date 2020-8-23 11:10:29
 */
public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, Long> {

}
