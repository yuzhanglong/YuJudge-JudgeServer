package com.yzl.yujudge.dto;

import java.util.List;

/**
 * 用户组权限编辑的数据传输对象
 *
 * @author yuzhanglong
 * @date 2020-8-23 13:22:45
 */
public class PermissionEditDTO {
    private List<Long> permissions;

    public List<Long> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Long> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "PermissionEditDTO{" +
                "permissions=" + permissions +
                '}';
    }
}
