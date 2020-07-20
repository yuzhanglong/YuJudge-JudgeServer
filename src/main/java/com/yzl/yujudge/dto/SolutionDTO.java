package com.yzl.yujudge.dto;

import javax.validation.constraints.NotNull;

/**
 * @author yuzhanglong
 * @date 2020-7-20 16:58:51
 * @description 解决方案的数据传输对象
 */
public class SolutionDTO {
    private Long id;
    @NotNull(message = "期望输入不得为空")
    private String stdIn;
    @NotNull(message = "期望输出不得为空")
    private String expectedStdOut;

    @Override
    public String toString() {
        return "SolutionDTO{" +
                "id=" + id +
                ", stdIn='" + stdIn + '\'' +
                ", expectedStdOut='" + expectedStdOut + '\'' +
                ", pkProblem=";
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
}
