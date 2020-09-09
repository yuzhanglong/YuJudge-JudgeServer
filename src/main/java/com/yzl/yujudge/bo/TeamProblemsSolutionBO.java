package com.yzl.yujudge.bo;

import java.util.List;
import java.util.Map;

/**
 * 某支队伍/某个用户
 * 在某个题目集中的问题解决情况统计业务对象
 *
 * @author yuzhanglong
 * @date 2020-9-9 11:51:52
 */
public class TeamProblemsSolutionBO {
    private List<Map<String, Object>> problemResult;
    private Integer totalAcAmount;
    private Long totalTimePenalty;

    public TeamProblemsSolutionBO(List<Map<String, Object>> problemResult, Integer totalAcAmount, Long totalTimePenalty) {
        this.problemResult = problemResult;
        this.totalAcAmount = totalAcAmount;
        this.totalTimePenalty = totalTimePenalty;
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

    public Long getTotalTimePenalty() {
        return totalTimePenalty;
    }

    public void setTotalTimePenalty(Long totalTimePenalty) {
        this.totalTimePenalty = totalTimePenalty;
    }

    @Override
    public String toString() {
        return "TeamProblemsSolutionBO{" +
                "problemResult=" + problemResult +
                ", totalAcAmount=" + totalAcAmount +
                '}';
    }
}
