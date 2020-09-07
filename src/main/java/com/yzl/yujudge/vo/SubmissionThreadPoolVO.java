package com.yzl.yujudge.vo;

/**
 * 提交相关线程池配置
 *
 * @author yuzhanglong
 * @date 2020-9-7 13:15:08
 */
public class SubmissionThreadPoolVO {
    private Integer maxPoolSize;
    private Integer workingAmount;
    private Integer maxQueueAmount;

    public SubmissionThreadPoolVO(Integer maxPoolSize, Integer workingAmount, Integer queueAmount) {
        this.maxPoolSize = maxPoolSize;
        this.workingAmount = workingAmount;
        this.maxQueueAmount = queueAmount;
    }

    public Integer getMaxQueueAmount() {
        return maxQueueAmount;
    }

    public void setMaxQueueAmount(Integer maxQueueAmount) {
        this.maxQueueAmount = maxQueueAmount;
    }

    public Integer getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(Integer maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public Integer getWorkingAmount() {
        return workingAmount;
    }

    public void setWorkingAmount(Integer workingAmount) {
        this.workingAmount = workingAmount;
    }

    @Override
    public String toString() {
        return "SubmissionThreadPoolVO{" +
                "maxPoolSize=" + maxPoolSize +
                ", workingAmount=" + workingAmount +
                '}';
    }
}
