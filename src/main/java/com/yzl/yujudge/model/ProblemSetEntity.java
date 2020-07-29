package com.yzl.yujudge.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

/**
 * @author yuzhanglong
 * @description 题目集实体类
 * @date 2020-7-26 23:58:30
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "problem_set", schema = "yu-judge")
public class ProblemSetEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "creator")
    private Integer creator;


    @ManyToMany
    @JoinTable(name = "problem_set_problem",
            joinColumns = @JoinColumn(name = "pk_problem_set"),
            inverseJoinColumns = @JoinColumn(name = "pk_problem"))
    private List<JudgeProblemEntity> judgeProblemEntityList;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public List<JudgeProblemEntity> getJudgeProblemEntityList() {
        return judgeProblemEntityList;
    }

    public void setJudgeProblemEntityList(List<JudgeProblemEntity> judgeProblemEntityList) {
        this.judgeProblemEntityList = judgeProblemEntityList;
    }
}
