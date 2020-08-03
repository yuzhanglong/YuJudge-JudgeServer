package com.yzl.yujudge.dto;

/**
 * @author yuzhanglong
 * @description 用户登录操作的数据传输对象
 * @date 2020-08-02 19:56:48
 */
public class LoginDTO {
    private String nickname;
    private String email;
    private String password;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
