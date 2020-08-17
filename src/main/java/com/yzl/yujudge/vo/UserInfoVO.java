package com.yzl.yujudge.vo;

/**
 * @author yuzhanglong
 * @description 用户信息的视图层对象
 * @date 2020-08-07 20:28:13
 */
public class UserInfoVO {
    private String nickname;
    private String email;
    private String avatar;
    private Integer acAmount;
    private Integer submissionAmount;
    private String scope;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getAcAmount() {
        return acAmount;
    }

    public void setAcAmount(Integer acAmount) {
        this.acAmount = acAmount;
    }

    public Integer getSubmissionAmount() {
        return submissionAmount;
    }

    public void setSubmissionAmount(Integer submissionAmount) {
        this.submissionAmount = submissionAmount;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Override
    public String toString() {
        return "UserInfoVO{" +
                "nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", acAmount=" + acAmount +
                ", submissionAmount=" + submissionAmount +
                ", scope='" + scope + '\'' +
                '}';
    }
}