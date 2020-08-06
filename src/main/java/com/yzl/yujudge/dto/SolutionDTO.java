package com.yzl.yujudge.dto;


import javax.validation.constraints.NotNull;

/**
 * @author yuzhanglong
 * @date 2020-7-20 16:58:51
 * @description 解决方案的数据传输对象
 */

public class SolutionDTO {
    @NotNull(message = "输入不得为空")
    private String stdIn;
    @NotNull(message = "期望输出不得为空")
    private String expectedStdOut;
    @NotNull(message = "描述不得为空")
    private String description;

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
        return "SolutionDTO{" +
                ", stdIn='" + stdIn + '\'' +
                ", expectedStdOut='" + expectedStdOut + '\'' +
                '}';
    }
}
