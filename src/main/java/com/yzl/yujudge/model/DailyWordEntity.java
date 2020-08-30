package com.yzl.yujudge.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * 每日一句实体类
 *
 * @author yuzhanglong
 * @date 2020-8-30 20:56:11
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "daily_word", schema = "yu-judge")
public class DailyWordEntity extends BaseEntity{
    @Basic
    @Column(name = "title")
    private String title;

    @Basic
    @Column(name = "content")
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
