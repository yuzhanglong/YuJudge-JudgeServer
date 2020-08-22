package com.yzl.yujudge.core.enumeration;

import java.util.stream.Stream;

/**
 * 基础用户组枚举类
 * 管理员可以动态决定用户组，
 * 但是为了保证项目的正常运行，我们强制设置了部分默认分钟
 *
 * @author yuzhanglong
 * @date 2020-8-22 18:12:26
 */

public enum BaseUserGroupEnum {
    // 系统管理员
    ROOT,
    // 一般用户
    COMMON;


    /**
     * 传入用户组名称，将其转化为枚举类
     *
     * @author yuzhanglong
     * @date 2020-8-22 18:19:22
     */
    public static BaseUserGroupEnum userGroupNameToEnum(String userGroupName) {
        return Stream.of(BaseUserGroupEnum.values())
                .filter(c -> c.toString().equals(userGroupName))
                .findAny()
                .orElse(null);
    }

    /**
     * 传入用户组名称，检查是否属于默认分组
     *
     * @author yuzhanglong
     * @date 2020-8-22 18:22:14
     */
    public static Boolean isDefaultUserGroup(String userGroupName) {
        return userGroupNameToEnum(userGroupName) != null;
    }
}
