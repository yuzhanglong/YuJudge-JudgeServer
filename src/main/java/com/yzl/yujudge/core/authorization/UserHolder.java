package com.yzl.yujudge.core.authorization;


import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhanglong
 * @description 全局用户信息
 * @date 2020-08-07 00:23:39
 */
public class UserHolder {
    private static final ThreadLocal<Map<String, Object>> USER_HOLDER_THREAD_LOCAL = new ThreadLocal<>();

    /**
     * @param userId 用户的id
     * @author yuzhanglong
     * @description 初始化用户threadLocal
     * @date 2020-08-07 11:48:59
     */
    public static void setUser(Long userId) {
        Map<String, Object> map = new HashMap<>(1);
        map.put("userId", userId);
        USER_HOLDER_THREAD_LOCAL.set(map);
    }

    /**
     * @author yuzhanglong
     * @description 销毁用户threadLocal
     * @date 2020-08-07 11:49:13
     */
    public static void remove() {
        USER_HOLDER_THREAD_LOCAL.remove();
    }

    /**
     * @return userId 用户id，如果不存在，则返回null
     * @author yuzhanglong
     * @description 从threadLocal中获取用户Id
     * @date 2020-08-07 11:49:30
     */
    public static Long getUserId() {
        Map<String, Object> map = USER_HOLDER_THREAD_LOCAL.get();
        return (Long) map.get("userId");
    }
}