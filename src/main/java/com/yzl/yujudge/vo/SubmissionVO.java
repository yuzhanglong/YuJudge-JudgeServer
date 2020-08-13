package com.yzl.yujudge.vo;


import java.util.Date;

/**
 * @author yuzhanglong
 * @description 分页相关的视图层对象
 * @date 2020-7-31 20:03:55
 */
public class SubmissionVO {
    private Long id;
    private String language;
    private String judgeCondition;
    private Long timeCost;
    private Long memoryCost;
    private Date createTime;
    private String judgePreference;
    private UserInfoVO creator;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getJudgeCondition() {
        return judgeCondition;
    }

    public void setJudgeCondition(String judgeCondition) {
        this.judgeCondition = judgeCondition;
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

    public String getJudgePreference() {
        return judgePreference;
    }

    public void setJudgePreference(String judgePreference) {
        this.judgePreference = judgePreference;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public UserInfoVO getCreator() {
        return creator;
    }

    public void setCreator(UserInfoVO creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "SubmissionVO{" +
                "id=" + id +
                ", language='" + language + '\'' +
                ", judgeCondition='" + judgeCondition + '\'' +
                ", timeCost=" + timeCost +
                ", memoryCost=" + memoryCost +
                ", judgePreference='" + judgePreference + '\'' +
                '}';
    }
}