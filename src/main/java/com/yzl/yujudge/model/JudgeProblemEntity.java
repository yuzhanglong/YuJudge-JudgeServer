package com.yzl.yujudge.model;

import javax.persistence.*;
import java.util.List;

/**
 * @author yuzhanglong
 * @date 2020-7-18 23:49:50
 * @description problem实体类
 */
@Entity
@Table(name = "judge_problem", schema = "yu-judge")
public class JudgeProblemEntity extends BaseEntity {
    private Long id;
    private String name;
    private String content;
    private Integer timeLimit;
    private Integer memoryLimit;
    private Integer cpuTimeLimit;


    public List<JudgeSolutionEntity> getJudgeSolutionEntityList() {
        return judgeSolutionEntityList;
    }

    public void setJudgeSolutionEntityList(List<JudgeSolutionEntity> judgeSolutionEntityList) {
        this.judgeSolutionEntityList = judgeSolutionEntityList;
    }

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "pk_problem")
    private List<JudgeSolutionEntity> judgeSolutionEntityList;

    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "time_limit")
    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    @Basic
    @Column(name = "memory_limit")
    public Integer getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(Integer memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    @Basic
    @Column(name = "cpu_time_limit")
    public Integer getCpuTimeLimit() {
        return cpuTimeLimit;
    }

    public void setCpuTimeLimit(Integer cpuTimeLimit) {
        this.cpuTimeLimit = cpuTimeLimit;
    }
}
