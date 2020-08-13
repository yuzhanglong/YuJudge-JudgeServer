package com.yzl.yujudge.bo;

import java.util.List;
import java.util.Map;

/**
 * @author yuzhanglong
 * @description 某支队伍/某个用户
 * 在某个题目集中的问题解决情况统计业务对象
 */
public class TeamProblemsSolutionBO {
    private List<Map<String, Object>> problemResult;
    private Integer totalAcAmount;

    public TeamProblemsSolutionBO(List<Map<String, Object>> problemResult, Integer totalAcAmount) {
        this.problemResult = problemResult;
        this.totalAcAmount = totalAcAmount;
    }

    public List<Map<String, Object>> getProblemResult() {
        return problemResult;
    }

    public void setProblemResult(List<Map<String, Object>> problemResult) {
        this.problemResult = problemResult;
    }

    public Integer getTotalAcAmount() {
        return totalAcAmount;
    }

    public void setTotalAcAmount(Integer totalAcAmount) {
        this.totalAcAmount = totalAcAmount;
    }

    @Override
    public String toString() {
        return "TeamProblemsSolutionBO{" +
                "problemResult=" + problemResult +
                ", totalAcAmount=" + totalAcAmount +
                '}';
    }
}
