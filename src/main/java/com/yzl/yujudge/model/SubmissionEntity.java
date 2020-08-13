package com.yzl.yujudge.model;

import com.yzl.yujudge.dto.JudgeResultDTO;
import com.yzl.yujudge.utils.converter.JudgeResultConverter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * @author yuzhanglong
 * @date 2020-7-29 00:29:16
 * @description 提交记录实体类
 */

@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "submission", schema = "yu-judge")
public class SubmissionEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "pk_problem")
    private Long pkProblem;


    @Basic
    @Column(name = "language")
    private String language;

    @Basic
    @Column(name = "judge_condition")
    private String judgeCondition;

    @Basic
    @Column(name = "time_cost")
    private Long timeCost;

    @Basic
    @Column(name = "memory_cost")
    private Long memoryCost;

    @Column(name = "judge_result")
    @Convert(converter = JudgeResultConverter.class)
    private JudgeResultDTO judgeResult;

    @Basic
    @Column(name = "code_content")
    private String codeContent;

    @Basic
    @Column(name = "judge_preference")
    private String judgePreference;

    @Basic
    @Column(name = "is_ac_before")
    private Boolean isAcBefore;

    @OneToOne
    @JoinColumn(name = "pk_problem_set", referencedColumnName = "id")
    private ProblemSetEntity problemSet;

    @OneToOne
    @JoinColumn(name = "pk_user", referencedColumnName = "id")
    private UserEntity creator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPkProblem() {
        return pkProblem;
    }

    public void setPkProblem(Long pkProblem) {
        this.pkProblem = pkProblem;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCondition() {
        return judgeCondition;
    }

    public void setCondition(String condition) {
        this.judgeCondition = condition;
    }

    public Long getTimeCost() {
        return timeCost;
    }

    public void setTimeCost(Long timeCost) {
        this.timeCost = timeCost;
    }

    public Long getMemoryCost() {
        return memoryCost;
    }

    public void setMemoryCost(Long memoryCost) {
        this.memoryCost = memoryCost;
    }

    public String getCodeContent() {
        return codeContent;
    }

    public void setCodeContent(String codeContent) {
        this.codeContent = codeContent;
    }

    public String getJudgeCondition() {
        return judgeCondition;
    }

    public void setJudgeCondition(String judgeCondition) {
        this.judgeCondition = judgeCondition;
    }

    public String getJudgePreference() {
        return judgePreference;
    }

    public void setJudgePreference(String judgePreference) {
        this.judgePreference = judgePreference;
    }

    public JudgeResultDTO getJudgeResult() {
        return judgeResult;
    }

    public void setJudgeResult(JudgeResultDTO judgeResult) {
        this.judgeResult = judgeResult;
    }

    public ProblemSetEntity getProblemSet() {
        return problemSet;
    }

    public void setProblemSet(ProblemSetEntity problemSet) {
        this.problemSet = problemSet;
    }

    public Boolean getIsAcBefore() {
        return isAcBefore;
    }

    public void setIsAcBefore(Boolean isAcBefore) {
        this.isAcBefore = isAcBefore;
    }

    public Boolean getAcBefore() {
        return isAcBefore;
    }

    public void setAcBefore(Boolean acBefore) {
        isAcBefore = acBefore;
    }

    public UserEntity getCreator() {
        return creator;
    }

    public void setCreator(UserEntity creator) {
        this.creator = creator;
    }
}
