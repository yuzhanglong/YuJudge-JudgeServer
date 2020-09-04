package com.yzl.yujudge.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

/**
 * 权限相关实体类
 *
 * @author yuzhanglong
 * @date 2020-8-22 10:24:38
 */

@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "permission", schema = "yu-judge")
public class PermissionEntity extends BaseEntity {
    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "permissions")
    private List<UserGroupEntity> userGroupEntityList;

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

    public List<UserGroupEntity> getUserGroupEntityList() {
        return userGroupEntityList;
    }

    public void setUserGroupEntityList(List<UserGroupEntity> userGroupEntityList) {
        this.userGroupEntityList = userGroupEntityList;
    }
}
