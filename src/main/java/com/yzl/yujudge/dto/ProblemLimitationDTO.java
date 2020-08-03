package com.yzl.yujudge.dto;

import com.yzl.yujudge.validators.annotations.LanguageListAccepted;

import javax.validation.Valid;
import java.util.List;

/**
 * @author yuzhanglong
 * @description problem限制数据传输对象
 * @date 2020-7-26 17:56:26
 */

@LanguageListAccepted
public class ProblemLimitationDTO {
    private Integer timeLimit;
    private Integer memoryLimit;
    private Integer cpuTimeLimit;
    private Integer outputLimit;

    @Valid
    private List<String> allowedLanguage;

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Integer getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(Integer memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public Integer getCpuTimeLimit() {
        return cpuTimeLimit;
    }

    public void setCpuTimeLimit(Integer cpuTimeLimit) {
        this.cpuTimeLimit = cpuTimeLimit;
    }

    public Integer getOutputLimit() {
        return outputLimit;
    }

    public void setOutputLimit(Integer outputLimit) {
        this.outputLimit = outputLimit;
    }

    public List<String> getAllowedLanguage() {
        return allowedLanguage;
    }

    public void setAllowedLanguage(List<String> allowedLanguage) {
        this.allowedLanguage = allowedLanguage;
    }

    @Override
    public String toString() {
        return "ProblemLimitationDTO{" +
                "timeLimit=" + timeLimit +
                ", memoryLimit=" + memoryLimit +
                ", cpuTimeLimit=" + cpuTimeLimit +
                ", outputLimit=" + outputLimit +
                '}';
    }
}
