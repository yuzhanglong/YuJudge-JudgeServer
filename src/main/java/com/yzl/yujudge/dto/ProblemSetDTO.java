package com.yzl.yujudge.dto;

import javax.validation.constraints.NotNull;
import java.util.Date;


/**
 * @author yuzhanglong
 * @description 创建题目集的数据传输对象
 * @date 2020-08-09 15:19:51
 */
public class ProblemSetDTO {
    @NotNull(message = "名称不得为空")
    private String name;
    @NotNull(message = "描述不得为空")
    private String description;
    @NotNull(message = "截止时间不得为空")
    private Date deadline;
    @NotNull(message = "开始时间不得为空")
    private Date startTime;

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

    @Override
    public String toString() {
        return "ProblemSetDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", startTime=" + startTime +
                '}';
    }
}
