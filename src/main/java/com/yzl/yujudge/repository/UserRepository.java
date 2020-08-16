package com.yzl.yujudge.repository;

import com.yzl.yujudge.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author yuzhanglong
 * @date 2020-08-03 13:51:49
 * @description 用户相关数据库操作接口
 */

public interface UserRepository extends JpaRepository<UserEntity, Long> {

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
     * 通过用户昵称或者邮箱找到对应用户
     *
     * @param nickname 用户昵称
     * @param email    用户邮箱
     * @return UserEntity 用户实体对象
     * @author yuzhanglong
     * @description 通过用户昵称或者邮箱找到对应用户
     * @date 2020-08-03 22:09:55
     */
    UserEntity findUserEntityByNicknameOrEmail(String nickname, String email);

    /**
     * 获取活跃用户
     *
     * @param pageable 分页参数
     * @return List UserEntity 多个用户实体对象
     * @author yuzhanglong
     * @description 通过用户昵称或者邮箱找到对应用户
     * @date 2020-08-07 16:22:05
     */
    List<UserEntity> findByOrderBySubmissionAmountDesc(Pageable pageable);


    /**
     * 通过用户id寻找对应的用户
     *
     * @param userId 要获取的用户id
     * @return UserEntity 用户实体对象
     * @author yuzhanglong
     * @description 通过用户id寻找对应的用户
     * @date 2020-08-08 12:58:04
     */
    UserEntity findOneById(Long userId);


    /**
     * 通过用户权限寻找对应的用户
     *
     * @param scope    用户权限
     * @param pageable 分页对象
     * @return UserEntity 用户实体对象
     * @author yuzhanglong
     * @description 通过用户权限寻找对应的用户
     * @date 2020-08-16 13:48:19
     */
    @Query("select user from UserEntity user " +
            "where user.scope = ?1 " +
            "order by user.createTime desc ")
    Page<UserEntity> findUsersByScope(String scope, Pageable pageable);
}