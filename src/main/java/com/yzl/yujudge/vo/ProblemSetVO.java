package com.yzl.yujudge.vo;


import java.util.Date;

/**
 * @author yuzhanglong
 * @description ProblemSet的视图层对象
 * @date 2020-08-08 22:04:52
 */
public class ProblemSetVO {
    private String name;
    private String description;
    private UserInfoVO creator;
    private Date deadline;
    private Date startTime;
    private Date createTime;
    private Long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserInfoVO getCreator() {
        return creator;
    }

    public void setCreator(UserInfoVO creator) {
        this.creator = creator;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ProblemSetVO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", creator=" + creator +
                ", deadline=" + deadline +
                ", startTime=" + startTime +
                '}';
    }
}
