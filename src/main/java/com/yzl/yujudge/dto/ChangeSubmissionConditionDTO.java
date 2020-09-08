package com.yzl.yujudge.dto;

import javax.validation.constraints.NotNull;

/**
 * 修改判题状态的数据传输对象
 *
 * @author yuzhanglong
 * @date 2020-9-8 22:34:50
 */
public class ChangeSubmissionConditionDTO {
    @NotNull(message = "修改状态不得为空")
    private String condition;
    @NotNull(message = "提交id不得为空")
    private Long submissionId;

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Long getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(Long submissionId) {
        this.submissionId = submissionId;
    }

    @Override
    public String toString() {
        return "ChangeSubmissionConditionDTO{" +
                "condition='" + condition + '\'' +
                ", submissionId=" + submissionId +
                '}';
    }
}
