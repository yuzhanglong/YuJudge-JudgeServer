package com.yzl.yujudge.vo;

import java.util.Map;

/**
 * @author yuzhanglong
 * @description submission的细节内容的视图层对象
 * @date 2020-8-1 11:58:46
 */
public class SubmissionDetailVO extends SubmissionVO{
    private String judgeResult;
    private String codeContent;

    public String getJudgeResult() {
        return judgeResult;
    }

    public void setJudgeResult(String judgeResult) {
        this.judgeResult = judgeResult;
    }

    public String getCodeContent() {
        return codeContent;
    }

    public void setCodeContent(String codeContent) {
        this.codeContent = codeContent;
    }

    @Override
    public String toString() {
        return "SubmissionDetailVO{" +
                "judgeResult='" + judgeResult + '\'' +
                ", codeContent='" + codeContent + '\'' +
                '}';
    }
}
