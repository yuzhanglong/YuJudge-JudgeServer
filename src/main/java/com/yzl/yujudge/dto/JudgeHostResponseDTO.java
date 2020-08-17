package com.yzl.yujudge.dto;

/**
 * @author yuzhanglong
 * @description 判题服务器返回基本格式的数据传输对象
 * code: 返回的错误码
 * message: 附带信息（如果没有则为null）
 * request: 本次请求的url地址
 */
public class JudgeHostResponseDTO {
    private String code;
    private String message;
    private String request;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    @Override
    public String toString() {
        return "JudgeHostResponseDTO{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", request='" + request + '\'' +
                '}';
    }
}
