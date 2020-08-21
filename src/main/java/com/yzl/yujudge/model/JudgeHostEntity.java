package com.yzl.yujudge.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * 判题服务器的实体类
 *
 * @author yuzhanglong
 * @date 2020-7-30 18:47
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "judge_host", schema = "yu-judge")
public class JudgeHostEntity extends BaseEntity {
    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "address")
    private String address;

    @Basic
    @Column(name = "is_active")
    private Boolean isActive;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "JudgeHostEntity{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
