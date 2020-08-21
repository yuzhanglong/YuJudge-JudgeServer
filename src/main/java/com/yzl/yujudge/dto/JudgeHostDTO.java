package com.yzl.yujudge.dto;

import javax.validation.constraints.NotNull;

/**
 * 判题服务器信息的数据传输对象
 *
 * @author yuzhanglong
 * @date 2020-7-30 19:29
 */
public class JudgeHostDTO {
    @NotNull(message = "服务器名称不得为空")
    private String name;
    @NotNull(message = "服务器地址不能为空")
    private String baseUrl;
    @NotNull(message = "端口不得为空")
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

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "JudgeHostDTO{" +
                "name='" + name + '\'' +
                ", address='" + baseUrl + '\'' +
                ", port=" + port +
                '}';
    }
}
