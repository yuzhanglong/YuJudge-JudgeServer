package com.yzl.yujudge.dto;

import java.util.List;
import java.util.Objects;

/**
 * 判题结果的数据传输对象，
 * 我们成功请求获得判题结果json字符串之后
 * 将其映射到这个对象上面
 *
 * @author yuzhanglong
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JudgeResultDTO that = (JudgeResultDTO) o;
        return Objects.equals(judgeResults, that.judgeResults) &&
                Objects.equals(submissionId, that.submissionId) &&
                Objects.equals(judgeEndTime, that.judgeEndTime) &&
                Objects.equals(extraInfo, that.extraInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(judgeResults, submissionId, judgeEndTime, extraInfo);
    }
}
