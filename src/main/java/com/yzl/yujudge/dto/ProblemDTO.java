package com.yzl.yujudge.dto;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author yuzhanglong
 * @description problem的数据传输对象
 * @date 2020-7-20 16:55:34
 */
public class ProblemDTO {
    @NotNull(message = "题目名称不得为空")
    private String name;

    @NotNull(message = "题目内容不得为空")
    private String content;
    private Integer timeLimit;
    private Integer memoryLimit;
    private Integer cpuTimeLimit;
    private List<String> characterTags;

    @Override
    public String toString() {
        return "ProblemDTO{" +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", timeLimit=" + timeLimit +
                ", memoryLimit=" + memoryLimit +
                ", cpuTimeLimit=" + cpuTimeLimit +
                ", characterTags='" + characterTags + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

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

    public List<String> getCharacterTags() {
        return characterTags;
    }

    public void setCharacterTags(List<String> characterTags) {
        this.characterTags = characterTags;
    }
}
