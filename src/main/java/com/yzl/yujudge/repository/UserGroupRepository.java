package com.yzl.yujudge.repository;

import com.yzl.yujudge.model.UserGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yuzhanglong
 * @date 2020-08-16 12:30:22
 * @description 用户组数据库操作集
 */
public interface UserGroupRepository extends JpaRepository<UserGroupEntity, Long> {

}
