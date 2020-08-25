package com.yzl.yujudge.repository;

import com.yzl.yujudge.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author yuzhanglong
 * @date 2020-08-03 13:51:49
 * @description 用户相关数据库操作接口
 */

public interface UserRepository extends SoftDeleteRepository<UserEntity> {

    /**
     * 通过用户昵称找到对应用户
     *
     * @param nickName 用户昵称
     * @return UserEntity 用户实体对象
     * @author yuzhanglong
     * @description 通过用户昵称找到对应用户
     * @date 2020-08-03 13:57:16
     */
    UserEntity findByNickname(String nickName);

    /**
     * 获取用户，根据提交数目排序
     *
     * @param pageable 分页参数
     * @return List UserEntity 多个用户实体对象
     * @author yuzhanglong
     * @date 2020-08-07 16:22:05
     */
    List<UserEntity> findByOrderBySubmissionAmountDesc(Pageable pageable);


    /**
     * 通过用户id寻找对应的用户
     *
     * @param userId 要获取的用户id
     * @return UserEntity 用户实体对象
     * @author yuzhanglong
     * @date 2020-08-08 12:58:04
     */
    UserEntity findOneById(Long userId);


    /**
     * 通过用户组id，获取其下的所有用户
     *
     * @param userGroupId 用户组id
     * @param pageable 分页对象
     * @return 用户实体类的分页对象
     * @author yuzhanglong
     * @date 2020-8-25 13:41:09
     */
    @Query("select userGroup.users from UserGroupEntity userGroup where userGroup.id = ?1 ")
    Page<UserEntity> findUsersByUserGroup(Long userGroupId, Pageable pageable);
}