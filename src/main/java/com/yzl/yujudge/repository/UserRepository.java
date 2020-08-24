package com.yzl.yujudge.repository;

import com.yzl.yujudge.model.UserEntity;
import org.springframework.data.domain.Pageable;

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
}