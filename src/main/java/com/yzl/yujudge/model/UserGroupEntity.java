package com.yzl.yujudge.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

/**
 * 用户组实体类
 *
 * @author yuzhanglong
 * @date 2020-08-16 11:56:20
 */

@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "user_group", schema = "yu-judge")
public class UserGroupEntity extends BaseEntity {
    @Basic
    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(name = "user_user_group",
            joinColumns = @JoinColumn(name = "pk_user_group"),
            inverseJoinColumns = @JoinColumn(name = "pk_user"))
    private List<UserEntity> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }
}
