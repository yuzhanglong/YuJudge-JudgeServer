package com.yzl.yujudge.dto;

/**
 * 这是判题结果数据传输对象的一部分，表示了一个测试案例
 *
 * @author yuzhanglong
 * @date 2020-9-4 17:17:29
 */
public class JudgeTestCaseResultDTO {
    private Integer realTimeCost;
    private Integer memoryCost;
    private Integer cpuTimeCost;
    private Integer condition;
    private String stdinPath;
    private String stdOutPath;
    private String stdErrPath;
    private String message;

    public Integer getRealTimeCost() {
        return realTimeCost;
    }

    public void setRealTimeCost(Integer realTimeCost) {
        this.realTimeCost = realTimeCost;
    }

    public Integer getMemoryCost() {
        return memoryCost;
    }

    public void setMemoryCost(Integer memoryCost) {
        this.memoryCost = memoryCost;
    }

    public Integer getCpuTimeCost() {
        return cpuTimeCost;
    }

    public void setCpuTimeCost(Integer cpuTimeCost) {
        this.cpuTimeCost = cpuTimeCost;
    }

    public Integer getCondition() {
        return condition;
    }

    public void setCondition(Integer condition) {
        this.condition = condition;
    }

    public String getStdinPath() {
        return stdinPath;
    }

    public void setStdinPath(String stdinPath) {
        this.stdinPath = stdinPath;
    }

    public String getStdOutPath() {
        return stdOutPath;
    }

    public void setStdOutPath(String stdOutPath) {
        this.stdOutPath = stdOutPath;
    }

    public String getStdErrPath() {
        return stdErrPath;
    }

    public void setStdErrPath(String stdErrPath) {
        this.stdErrPath = stdErrPath;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "JudgeTestCaseResultDTO{" +
                "realTimeCost=" + realTimeCost +
                ", memoryCost=" + memoryCost +
                ", cpuTimeCost=" + cpuTimeCost +
                ", condition=" + condition +
                ", stdinPath='" + stdinPath + '\'' +
                ", stdOutPath='" + stdOutPath + '\'' +
                ", stdErrPath='" + stdErrPath + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
