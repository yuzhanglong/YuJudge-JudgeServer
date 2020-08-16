package com.yzl.yujudge.core.enumeration;

import java.util.stream.Stream;

/**
 * @author yuzhanglong
 * @description 用户类型的枚举类
 * @date 2020-08-16 13:06:36
 */

public enum UserScopeEnum {
    // 管理员
    ADMIN,

    // 一般管理员,
    MANAGER,

    // 一般用户
    COMMON,

    // 临时用户
    TEMPORARY;

    /**
     * @param scope 权限
     * @author yuzhanglong
     * @description 权限转枚举类
     * @date 2020-08-16 14:04:54
     */
    public static UserScopeEnum toUserScope(String scope) {
        return Stream.of(UserScopeEnum.values())
                .filter(c -> c.toString().equals(scope))
                .findAny()
                .orElse(null);
    }

    /**
     * @param scope 权限
     * @author yuzhanglong
     * @description 判断权限名称是否合法
     * @date 2020-08-16 14:05:14
     */
    public static Boolean isCorrectScope(String scope) {
        return toUserScope(scope.toUpperCase()) != null;
    }
}
