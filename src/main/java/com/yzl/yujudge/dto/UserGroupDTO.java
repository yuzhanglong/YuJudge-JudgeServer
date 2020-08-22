package com.yzl.yujudge.dto;

import javax.validation.constraints.NotNull;

/**
 * 用户组相关数据传输对象
 *
 * @author yuzhanglong
 * @date 2020-8-22 14:20:44
 */

public class UserGroupDTO {
    @NotNull(message = "用户组名称不得为空")
    private String name;
    @NotNull(message = "用户组描述不得为空")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "UserGroupDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
