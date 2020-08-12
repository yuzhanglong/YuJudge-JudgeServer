package com.yzl.yujudge.bo;

/**
 * @author yuzhanglong
 * @description 单个判题服务器信息的业务对象
 * @date 2020-08-08 15:10
 */

public class JudgeHostBO {
    private String name;
    private String address;
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
        return "JudgeHostBO{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
