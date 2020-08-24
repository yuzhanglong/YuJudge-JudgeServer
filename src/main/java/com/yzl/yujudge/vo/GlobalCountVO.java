package com.yzl.yujudge.vo;

import java.util.List;
import java.util.Map;

/**
 * 全局统计视图层对象
 *
 * @author yuzhanglong
 * @date 2020-8-24 18:28:24
 */
public class GlobalCountVO {
    private Long problemAmount;
    private Long problemSetAmount;
    private Long submissionAmount;
    private Long userAmount;
    private Long judgeHostAmount;
    private List<Map<String, Object>>  recentSubmission;

    public GlobalCountVO(
            Long problemAmount,
            Long problemSetAmount,
            Long submissionAmount,
            Long userAmount,
            Long judgeHostAmount,
            List<Map<String, Object>> recentSubmission) {
        this.problemAmount = problemAmount;
        this.problemSetAmount = problemSetAmount;
        this.submissionAmount = submissionAmount;
        this.userAmount = userAmount;
        this.judgeHostAmount = judgeHostAmount;
        this.recentSubmission = recentSubmission;
    }


    public Long getProblemAmount() {
        return problemAmount;
    }

    public void setProblemAmount(Long problemAmount) {
        this.problemAmount = problemAmount;
    }

    public Long getProblemSetAmount() {
        return problemSetAmount;
    }

    public void setProblemSetAmount(Long problemSetAmount) {
        this.problemSetAmount = problemSetAmount;
    }

    public Long getSubmissionAmount() {
        return submissionAmount;
    }

    public void setSubmissionAmount(Long submissionAmount) {
        this.submissionAmount = submissionAmount;
    }

    public Long getUserAmount() {
        return userAmount;
    }

    public void setUserAmount(Long userAmount) {
        this.userAmount = userAmount;
    }

    public Long getJudgeHostAmount() {
        return judgeHostAmount;
    }

    public void setJudgeHostAmount(Long judgeHostAmount) {
        this.judgeHostAmount = judgeHostAmount;
    }

    public List<Map<String, Object>>  getRecentSubmission() {
        return recentSubmission;
    }

    public void setRecentSubmission(List<Map<String, Object>>  recentSubmission) {
        this.recentSubmission = recentSubmission;
    }

    @Override
    public String toString() {
        return "GlobalCountVO{" +
                "problemAmount=" + problemAmount +
                ", problemSetAmount=" + problemSetAmount +
                ", submissionAmount=" + submissionAmount +
                ", userAmount=" + userAmount +
                ", judgeHostAmount=" + judgeHostAmount +
                '}';
    }
}
