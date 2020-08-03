package com.yzl.yujudge.repository;

import com.yzl.yujudge.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

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
}
