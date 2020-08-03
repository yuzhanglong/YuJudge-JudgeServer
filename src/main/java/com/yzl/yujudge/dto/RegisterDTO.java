package com.yzl.yujudge.dto;

/**
 * @author yuzhanglong
 * @date 2020-08-02 19:59:42
 * @description 注册操作的数据传输对象
 */
public class RegisterDTO extends LoginDTO {
    private String checkCode;

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    @Override
    public String toString() {
        return "RegisterDTO{" +
                "checkCode='" + checkCode + '\'' +
                '}';
    }
}
