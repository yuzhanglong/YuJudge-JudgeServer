package com.yzl.yujudge.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * 公告相关实体类
 *
 * @author yuzhanglong
 * @date 2020-8-24 23:54:11
 */

@Entity
@DynamicUpdate
@DynamicInsert
@Where(clause = "delete_time is null")
@Table(name = "notice", schema = "yu-judge")
public class NoticeEntity extends SoftDeleteEntity {
    @Basic
    @Column(name = "title")
    private String title;

    @Basic
    @Column(name = "priority")
    private String priority;

    @OneToOne
    @JoinColumn(name = "pk_user", referencedColumnName = "id")
    private UserEntity creator;

    @Basic
    @Column(name = "is_closed")
    private Boolean closed;

    @Basic
    @Column(name = "content")
    private String content;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public UserEntity getCreator() {
        return creator;
    }

    public void setCreator(UserEntity pkUser) {
        this.creator = pkUser;
    }

    public Boolean getClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }
}
