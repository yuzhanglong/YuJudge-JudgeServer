package com.yzl.yujudge.dto;

import javax.validation.constraints.NotNull;


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
    private Long deadline;
    @NotNull(message = "开始时间不得为空")
    private Long startTime;

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

    public Long getDeadline() {
        return deadline;
    }

    public void setDeadline(Long deadline) {
        this.deadline = deadline;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
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
