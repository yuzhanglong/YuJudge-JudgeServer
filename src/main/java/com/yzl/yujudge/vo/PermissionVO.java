package com.yzl.yujudge.vo;

/**
 * 权限分配相关的视图层对象
 *
 * @author yuzhanglong
 * @date 2020-8-23 11:17:56
 */
public class PermissionVO {
    private String name;
    private String description;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        return "PermissionVO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
