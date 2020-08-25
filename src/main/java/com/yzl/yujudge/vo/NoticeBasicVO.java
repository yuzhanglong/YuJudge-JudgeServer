package com.yzl.yujudge.vo;

import com.yzl.yujudge.core.enumeration.NoticePriorityEnum;

/**
 * 公告基本内容视图层对象
 *
 * @author yuzhanglong
 * @date 2020-8-25 23:52:24
 */

public class NoticeBasicVO {
    private String title;
    private NoticePriorityEnum priority;
    private UserInfoBasicVO creator;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public NoticePriorityEnum getPriority() {
        return priority;
    }

    public void setPriority(NoticePriorityEnum priority) {
        this.priority = priority;
    }

    public UserInfoBasicVO getCreator() {
        return creator;
    }

    public void setCreator(UserInfoBasicVO creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "NoticeBasicVO{" +
                "title='" + title + '\'' +
                ", priority=" + priority +
                ", creator=" + creator +
                '}';
    }
}
