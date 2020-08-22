package com.yzl.yujudge.vo;

import java.util.List;

/**
 * @author yuzhanglong
 * @description 用户信息的视图层对象
 * @date 2020-08-07 20:28:13
 */
public class UserInfoVO {
    private Long id;
    private String nickname;
    private String email;
    private String avatar;
    private Integer acAmount;
    private Integer submissionAmount;
    private List<UserGroupVO> userGroups;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<UserGroupVO> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(List<UserGroupVO> userGroups) {
        this.userGroups = userGroups;
    }

    @Override
    public String toString() {
        return "UserInfoVO{" +
                "nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", acAmount=" + acAmount +
                ", submissionAmount=" + submissionAmount +
                '}';
    }
}