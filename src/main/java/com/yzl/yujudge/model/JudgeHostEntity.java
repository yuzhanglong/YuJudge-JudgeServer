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
    @Column(name = "base_url")
    private String baseUrl;

    @Basic
    @Column(name = "is_active")
    private Boolean isActive;

    @Basic
    @Column(name = "port")
    private Integer port;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String ipAddress) {
        this.baseUrl = ipAddress;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
