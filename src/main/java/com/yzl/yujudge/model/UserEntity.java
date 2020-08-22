package com.yzl.yujudge.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

/**
 * 用户实体类
 *
 * @author yuzhanglong
 * @date 2020-8-2 19:39
 */

@Entity
@DynamicUpdate
@DynamicInsert
@Where(clause = "delete_time is null")
@Table(name = "user", schema = "yu-judge")
public class UserEntity extends SoftDeleteEntity {
    @Basic
    @Column(name = "nickname")
    private String nickname;

    @Basic
    @Column(name = "password")
    private String password;

    @Basic
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "avatar")
    private String avatar;

    @Basic
    @Column(name = "ac_amount")
    private Integer acAmount;


    @Basic
    @Column(name = "submission_amount")
    private Integer submissionAmount;

    @ManyToMany(mappedBy = "participants")
    private List<ProblemSetEntity> problemSetEntityList;

    @ManyToMany(mappedBy = "users")
    private List<UserGroupEntity> userGroups;


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String name) {
        this.nickname = name;
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

    public List<ProblemSetEntity> getProblemSetEntityList() {
        return problemSetEntityList;
    }

    public void setProblemSetEntityList(List<ProblemSetEntity> problemSetEntityList) {
        this.problemSetEntityList = problemSetEntityList;
    }

    public List<UserGroupEntity> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(List<UserGroupEntity> userGroupEntityList) {
        this.userGroups = userGroupEntityList;
    }
}
