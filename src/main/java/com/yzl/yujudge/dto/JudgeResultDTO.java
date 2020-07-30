package com.yzl.yujudge.dto;

import java.util.List;

/**
 * @author yuzhanglong
 * @description 判题结果的数据传输对象，
 * 我们成功请求获得判题结果json字符串之后
 * 将其映射到这个对象上面
 */
public class JudgeResultDTO {
    private List<JudgeTestCaseResultDTO> judgeResults;
    private String submissionId;
    private Long judgeEndTime;
    private List<String> extraInfo;

    public List<JudgeTestCaseResultDTO> getJudgeResults() {
        return judgeResults;
    }

    public void setJudgeResults(List<JudgeTestCaseResultDTO> judgeResults) {
        this.judgeResults = judgeResults;
    }

    public String getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(String submissionId) {
        this.submissionId = submissionId;
    }

    public Long getJudgeEndTime() {
        return judgeEndTime;
    }

    public void setJudgeEndTime(Long judgeEndTime) {
        this.judgeEndTime = judgeEndTime;
    }

    public List<String> getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(List<String> extraInfo) {
        this.extraInfo = extraInfo;
    }

    @Override
    public String toString() {
        return "JudgeResultDTO{" +
                "judgeResults=" + judgeResults +
                ", submissionId='" + submissionId + '\'' +
                ", judgeEndTime=" + judgeEndTime +
                ", extraInfo=" + extraInfo +
                '}';
    }
}
