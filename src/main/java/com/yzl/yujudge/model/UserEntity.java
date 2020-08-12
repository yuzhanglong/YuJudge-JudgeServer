package com.yzl.yujudge.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

/**
 * @author yuzhanglong
 * @description 用户表实体类
 * @date 2020-8-2 19:39
 */

@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "user", schema = "yu-judge")
public class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
