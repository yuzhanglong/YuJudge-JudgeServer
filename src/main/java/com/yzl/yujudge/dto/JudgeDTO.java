package com.yzl.yujudge.dto;

import java.util.List;

/**
 * @author yuzhanglong
 * @description 请求判题服务器相关的数据传输对象
 * @date 2020-7-29 19:06:50
 */
public class JudgeDTO {
    private String submissionCode;
    private Integer realTimeLimit;
    private Integer cpuTimeLimit;
    private Integer memoryLimit;
    private Integer outputLimit;
    private String language;
    private String judgePreference;
    private List<SolutionDTO> resolutions;

    @Override
    public String toString() {
        return "JudgeDTO{" +
                "submissionCode='" + submissionCode + '\'' +
                ", realTimeLimit=" + realTimeLimit +
                ", cpuTimeLimit=" + cpuTimeLimit +
                ", memoryLimit=" + memoryLimit +
                ", outputLimit=" + outputLimit +
                ", language='" + language + '\'' +
                ", judgePreference='" + judgePreference + '\'' +
                ", resolutions=" + resolutions +
                '}';
    }
}
