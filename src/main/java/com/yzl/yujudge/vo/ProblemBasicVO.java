package com.yzl.yujudge.vo;


import java.util.Date;
import java.util.List;

/**
 * @author yuzhanglong
 * @description 题目基本信息（不包含解决方案/具体内容）的视图层对象
 * @date 2020-7-19 12:13
 */
public class ProblemBasicVO {
    private Long id;
    private String name;
    private List<String> characterTags;
    private Date createTime;
    private Boolean closed;


    public Boolean getClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
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

    @Override
    public String toString() {
        return "ProblemBasicVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", characterTags=" + characterTags +
                ", createTime=" + createTime +
                ", closed=" + closed +
                '}';
    }
}
