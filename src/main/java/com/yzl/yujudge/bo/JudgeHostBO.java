package com.yzl.yujudge.bo;

import java.util.Date;

/**
 * @author yuzhanglong
 * @description 单个判题服务器信息的业务对象
 * @date 2020-08-08 15:10
 */

public class JudgeHostBO {
    private String name;
    private String address;
    private Boolean active;
    private Date createTime;
    private Boolean connection;
    private Integer cpuCostPercentage;
    private Integer memoryPercentage;

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
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getConnection() {
        return connection;
    }

    public void setConnection(Boolean connection) {
        this.connection = connection;
    }

    public Integer getCpuCostPercentage() {
        return cpuCostPercentage;
    }

    public void setCpuCostPercentage(Integer cpuCostPercentage) {
        this.cpuCostPercentage = cpuCostPercentage;
    }

    public Integer getMemoryPercentage() {
        return memoryPercentage;
    }

    public void setMemoryPercentage(Integer memoryPercentage) {
        this.memoryPercentage = memoryPercentage;
    }

    @Override
    public String toString() {
        return "JudgeHostBO{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", active=" + active +
                ", createTime=" + createTime +
                ", connection=" + connection +
                '}';
    }
}
