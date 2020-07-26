package com.yzl.yujudge.dto;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author yuzhanglong
 * @description problem的数据传输对象
 * @date 2020-7-20 16:55:34
 */

public class ProblemDTO {
    @NotNull(message = "题目名称不得为空")
    private String name;

    @NotNull(message = "题目内容不得为空")
    private String content;

    private List<String> characterTags;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getCharacterTags() {
        return characterTags;
    }

    public void setCharacterTags(List<String> characterTags) {
        this.characterTags = characterTags;
    }

    @Override
    public String toString() {
        return "ProblemDTO{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", characterTags=" + characterTags +
                '}';
    }
}
