package com.yzl.yujudge.dto;

import javax.validation.constraints.NotNull;

/**
 * 用户数据传输对象
 *
 * @author yuzhanglong
 * @date 2020-9-13 23:09:15
 */
public class UserDTO {
    @NotNull(message = "昵称不得为空")
    private String nickname;
    @NotNull(message = "密码不得为空")
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

    @Override
    public String toString() {
        return "UserDTO{" +
                "nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
