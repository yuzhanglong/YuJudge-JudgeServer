package com.yzl.yujudge.dto;

import java.util.List;

/**
 * @author yuzhanglong
 * @description 更新题目集problem的数据传输对象
 * @date 2020-08-10 10:15:23
 */
public class UpdateProblemSetProblemDTO {
    private List<Long> problems;
    private Long problemSetId;

    public List<Long> getProblems() {
        return problems;
    }

    public void setProblems(List<Long> problems) {
        this.problems = problems;
    }

    public Long getProblemSetId() {
        return problemSetId;
    }

    public void setProblemSetId(Long problemSetId) {
        this.problemSetId = problemSetId;
    }

    @Override
    public String toString() {
        return "UpdateProblemSetProblemDTO{" +
                "problemId=" + problems +
                '}';
    }
}
