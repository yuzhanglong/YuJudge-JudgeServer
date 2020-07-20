package com.yzl.yujudge.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

/**
 * @author yuzhanglong
 * @date 2020-7-18 23:49:50
 * @description problem实体类
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "judge_problem", schema = "yu-judge")
public class JudgeProblemEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "content")
    private String content;

    @Basic
    @Column(name = "time_limit")
    private Integer timeLimit;

    @Basic
    @Column(name = "memory_limit")
    private Integer memoryLimit;

    @Basic
    @Column(name = "cpu_time_limit")
    private Integer cpuTimeLimit;

    @Basic
    @Column(name = "character_tags")
    private String characterTags;

    @OneToMany
    @JoinColumn(name = "pk_problem", referencedColumnName = "id")
    private List<JudgeSolutionEntity> solutions;

    public List<JudgeSolutionEntity> getJudgeSolutionEntityList() {
        return solutions;
    }

    public void setJudgeSolutionEntityList(List<JudgeSolutionEntity> solutions) {
        this.solutions = solutions;
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

    public List<String> getCharacterTags() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(characterTags, new TypeReference<List<String>>() {});
    }

    public void setCharacterTags(String characterTags) {
        this.characterTags = characterTags ;
    }
}
