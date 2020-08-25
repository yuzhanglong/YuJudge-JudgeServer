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
    private Integer priority;

    @OneToOne
    @JoinColumn(name = "pk_user", referencedColumnName = "id")
    private UserEntity pkUser;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public UserEntity getPkUser() {
        return pkUser;
    }

    public void setPkUser(UserEntity pkUser) {
        this.pkUser = pkUser;
    }

    @Override
    public String toString() {
        return "NoticeEntity{" +
                "title='" + title + '\'' +
                ", priority=" + priority +
                ", pkUser=" + pkUser +
                '}';
    }
}
