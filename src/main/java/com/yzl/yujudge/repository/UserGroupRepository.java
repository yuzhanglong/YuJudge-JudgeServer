package com.yzl.yujudge.repository;

import com.yzl.yujudge.model.UserGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yuzhanglong
 * @date 2020-08-16 12:30:22
 * @description 用户组数据库操作集
 */
public interface UserGroupRepository extends JpaRepository<UserGroupEntity, Long> {

    /**
     * 通过用户组id查找用户组
     *
     * @param id 用户组id
     * @return 查找的用户组实体类
     */
    UserGroupEntity findOneById(Long id);
}
