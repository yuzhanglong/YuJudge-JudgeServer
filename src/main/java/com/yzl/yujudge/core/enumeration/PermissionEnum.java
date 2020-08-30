package com.yzl.yujudge.core.enumeration;

/**
 * 权限枚举类
 *
 * @author yuzhanglong
 * @date 2020-8-30 12:17:04
 */

public enum PermissionEnum {
    // 系统管理员
    ADMIN("系统管理员"),

    // 题目管理
    PROBLEM_MANAGER("管理题目和题目集"),

    // 公告、信息管理
    NOTICE_MANGER("公告、信息管理"),

    // 一切用户(登录用户)
    ANY("一切用户");

    private final String description;

    PermissionEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
