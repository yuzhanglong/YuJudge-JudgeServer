package com.yzl.yujudge.vo;

/**
 * @author yuzhanglong
 * @description 判题服务器信息的视图层对象
 * @date 2020-7-30 19:07
 */
public class JudgeHostVO {
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
        return "JudgeHostVO{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
