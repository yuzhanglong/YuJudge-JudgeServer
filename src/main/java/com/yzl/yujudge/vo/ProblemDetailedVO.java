package com.yzl.yujudge.vo;

import java.util.Date;
import java.util.List;

/**
 * @author yuzhanglong
 * @description problem详细信息的视图层对象
 * @date 2020-7-21 23:49
 */
public class ProblemDetailedVO {
    private Long id;
    private String name;
    private String content;
    private Integer timeLimit;
    private Integer memoryLimit;
    private Integer cpuTimeLimit;
    private List<String> characterTags;
    private Date createTime;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getCharacterTags() {
        return characterTags;
    }

    public void setCharacterTags(List<String> characterTags) {
        this.characterTags = characterTags;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "ProblemBasicVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", timeLimit=" + timeLimit +
                ", memoryLimit=" + memoryLimit +
                ", cpuTimeLimit=" + cpuTimeLimit;
    }
}
