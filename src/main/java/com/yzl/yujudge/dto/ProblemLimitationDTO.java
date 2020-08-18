package com.yzl.yujudge.dto;


import javax.validation.constraints.NotNull;

/**
 * @author yuzhanglong
 * @description problem限制数据传输对象
 * @date 2020-7-26 17:56:26
 */

public class ProblemLimitationDTO {
    @NotNull(message = "时间限制不得为空")
    private Integer timeLimit;
    @NotNull(message = "内存限制不得为空")
    private Integer memoryLimit;
    private Integer cpuTimeLimit;
    @NotNull(message = "输出限制不得为空")
    private Integer outputLimit;


    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Integer getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(Integer memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public Integer getCpuTimeLimit() {
        return cpuTimeLimit;
    }

    public void setCpuTimeLimit(Integer cpuTimeLimit) {
        this.cpuTimeLimit = cpuTimeLimit;
    }

    public Integer getOutputLimit() {
        return outputLimit;
    }

    public void setOutputLimit(Integer outputLimit) {
        this.outputLimit = outputLimit;
    }

    @Override
    public String toString() {
        return "ProblemLimitationDTO{" +
                "timeLimit=" + timeLimit +
                ", memoryLimit=" + memoryLimit +
                ", cpuTimeLimit=" + cpuTimeLimit +
                ", outputLimit=" + outputLimit +
                '}';
    }
}
