package com.yzl.yujudge.core.authorization;


import com.yzl.yujudge.model.UserEntity;
import com.yzl.yujudge.model.UserGroupEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 全局用户信息
 *
 * @author yuzhanglong
 * @date 2020-08-07 00:23:39
 */
public class UserHolder {
    private static final ThreadLocal<Map<String, Object>> USER_HOLDER_THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 初始化用户threadLocal
     *
     * @param userEntity 用户的实体类
     * @author yuzhanglong
     * @date 2020-08-07 11:48:59
     */
    public static void setUser(UserEntity userEntity) {
        Map<String, Object> map = new HashMap<>(1);
        map.put("userEntity", userEntity);
        USER_HOLDER_THREAD_LOCAL.set(map);
    }

    /**
     * 初始化用户组
     *
     * @param userGroupEntityList 用户组实体类列表
     * @author yuzhanglong
     * @date 2020-08-07 11:48:59
     */
    public static void setUserGroup(List<UserGroupEntity> userGroupEntityList) {
        USER_HOLDER_THREAD_LOCAL.get().put("userGroups", userGroupEntityList);
    }

    /**
     * 销毁用户threadLocal
     *
     * @author yuzhanglong
     * @date 2020-08-07 11:49:13
     */
    public static void remove() {
        USER_HOLDER_THREAD_LOCAL.remove();
    }

    /**
     * 从threadLocal中获取用户Id
     *
     * @return userId 用户id，如果不存在，则返回null
     * @author yuzhanglong
     * @date 2020-08-07 11:49:30
     */
    public static Long getUserId() {
        Map<String, Object> map = USER_HOLDER_THREAD_LOCAL.get();
        return ((UserEntity) map.get("userEntity")).getId();
    }

    /**
     * 获取用户所属的用户组列表
     *
     * @return 用户所属的用户组列表
     * @author yuzhanglong
     * @date 2020-9-6 23:39:22
     */
    public static List<UserGroupEntity> getUserUserGroups() {
        Map<String, Object> map = USER_HOLDER_THREAD_LOCAL.get();
        return ((UserEntity) map.get("userEntity")).getUserGroups();
    }
}