package com.yzl.yujudge.dto;

import javax.validation.constraints.NotNull;

/**
 * @author yuzhanglong
 * @description 判题服务器信息的数据传输对象
 * @date 2020-7-30 19:29
 */
public class JudgeHostDTO {
    @NotNull(message = "服务器名称不得为空")
    private String name;
    @NotNull(message = "服务器地址不能为空")
    private String address;

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

    @Override
    public String toString() {
        return "JudgeHostDTO{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}