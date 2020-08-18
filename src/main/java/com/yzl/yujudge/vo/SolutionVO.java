package com.yzl.yujudge.vo;

import java.util.Date;

/**
 * @author yuzhanglong
 * @description 解决方案视图层对象
 * @date 2020-7-21 23:53
 */
public class SolutionVO {
    private Long id;
    private String stdIn;
    private String expectedStdOut;
    private Date createTime;
    private String description;

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

    public String getStdIn() {
        return stdIn;
    }

    public void setStdIn(String stdIn) {
        this.stdIn = stdIn;
    }

    public String getExpectedStdOut() {
        return expectedStdOut;
    }

    public void setExpectedStdOut(String expectedStdOut) {
        this.expectedStdOut = expectedStdOut;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "SolutionVO{" +
                "id=" + id +
                ", stdIn='" + stdIn + '\'' +
                ", expectedStdOut='" + expectedStdOut + '\'' +
                ", createTime=" + createTime +
                ", description='" + description + '\'' +
                '}';
    }
}
