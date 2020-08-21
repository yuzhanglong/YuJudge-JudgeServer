package com.yzl.yujudge.model;

import com.yzl.yujudge.utils.converter.ListJsonConverter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
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
public class ProblemSetEntity extends BaseEntity {
    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "deadline")
    private Date deadline;

    @Basic
    @Column(name = "start_time")
    private Date startTime;

    @Basic
    @Column(name = "judge_preference")
    private String judgePreference;

    @Basic
    @Column(name = "time_penalty")
    private Long timePenalty;

    @Convert(converter = ListJsonConverter.class)
    @Column(name = "allowed_language")
    private List<String> allowedLanguage;

    @ManyToMany
    @JoinTable(name = "problem_set_problem",
            joinColumns = @JoinColumn(name = "pk_problem_set"),
            inverseJoinColumns = @JoinColumn(name = "pk_problem"))
    private List<JudgeProblemEntity> problems;

    @ManyToMany
    @JoinTable(name = "problem_set_user",
            joinColumns = @JoinColumn(name = "pk_problem_set"),
            inverseJoinColumns = @JoinColumn(name = "pk_user"))
    private List<UserEntity> participants;

    @OneToOne
    @JoinColumn(name = "pk_user", referencedColumnName = "id")
    private UserEntity creator;


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


    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public UserEntity getCreator() {
        return creator;
    }

    public void setCreator(UserEntity creator) {
        this.creator = creator;
    }

    public List<JudgeProblemEntity> getProblems() {
        return problems;
    }

    public void setProblems(List<JudgeProblemEntity> problems) {
        this.problems = problems;
    }

    public List<String> getAllowedLanguage() {
        return allowedLanguage;
    }

    public void setAllowedLanguage(List<String> allowedLanguage) {
        this.allowedLanguage = allowedLanguage;
    }

    public String getJudgePreference() {
        return judgePreference;
    }

    public void setJudgePreference(String judgePreference) {
        this.judgePreference = judgePreference;
    }

    public List<UserEntity> getParticipants() {
        return participants;
    }

    public void setParticipants(List<UserEntity> participants) {
        this.participants = participants;
    }

    public Long getTimePenalty() {
        return timePenalty;
    }

    public void setTimePenalty(Long timePenalty) {
        this.timePenalty = timePenalty;
    }

    @Override
    public String toString() {
        return "ProblemSetEntity{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", startTime=" + startTime +
                ", judgePreference='" + judgePreference + '\'' +
                ", timePenalty=" + timePenalty +
                ", allowedLanguage=" + allowedLanguage +
                ", problems=" + problems +
                ", participants=" + participants +
                ", creator=" + creator +
                '}';
    }
}
