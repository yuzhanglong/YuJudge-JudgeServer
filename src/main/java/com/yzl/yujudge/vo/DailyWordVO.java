package com.yzl.yujudge.vo;

/**
 * 每日一句实视图层对象
 *
 * @author yuzhanglong
 * @date 2020-8-30 21:03:54
 */
public class DailyWordVO {
    private String title;
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

    @Override
    public String toString() {
        return "DailyWordVO{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
