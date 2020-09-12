package com.yzl.yujudge.dto;

import javax.validation.constraints.NotNull;

/**
 * 提交频率数据传输对象
 *
 * @author yuzhanglong
 * @date 2020-9-12 15:29:17
 */
public class SubmissionFrequencyDTO {
    @NotNull(message = "修改频率不得为空")
    private Long frequency;

    public Long getFrequency() {
        return frequency;
    }

    public void setFrequency(Long frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "SubmissionFrequencyDTO{" +
                "frequency=" + frequency +
                '}';
    }
}
