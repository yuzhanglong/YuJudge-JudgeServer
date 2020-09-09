package com.yzl.yujudge.utils;

import com.yzl.yujudge.core.enumeration.JudgeResultEnum;
import com.yzl.yujudge.dto.JudgeResultDTO;
import com.yzl.yujudge.dto.JudgeTestCaseResultDTO;

import java.util.List;

/**
 * 判题结果数据处理的工具类
 *
 * @author yuzhanglong
 * @date 2020-7-30 12:57
 */
public class JudgeResultCalculateUtil {
    private JudgeResultDTO judgeResult;
    private Integer timeCost;
    private Integer memoryCost;

    public JudgeResultCalculateUtil(JudgeResultDTO judgeResult) {
        this.judgeResult = judgeResult;
    }

    /**
     * 执行计算方案
     *
     * @author yuzhanglong
     * @date 2020-7-30 13:19
     */
    public void executeCalculate() {
        countMaxTimeAndMaxMemoryCost();
    }

    /**
     * 获取最大的时间消耗和内存消耗
     *
     * @author yuzhanglong
     * @date 2020-7-30 13:18
     */
    private void countMaxTimeAndMaxMemoryCost() {
        List<JudgeTestCaseResultDTO> testCaseResultDTOList = judgeResult.getJudgeResults();
        int time = 0;
        int memory = 0;
        for (JudgeTestCaseResultDTO testCase : testCaseResultDTOList) {
            int maxTime = countMaxTime(testCase);
            int maxMemory = countMaxMemory(testCase);
            if (maxTime > time) {
                time = maxTime;
            }
            if (maxMemory > memory) {
                memory = maxMemory;
            }
        }
        setTimeCost(time);
        setMemoryCost(memory);
    }

    /**
     * 获取最大时间消耗
     *
     * @author yuzhanglong
     * @date 2020-7-31 9:36
     */
    private Integer countMaxTime(JudgeTestCaseResultDTO testCase) {
        Integer cpuTimeCost = testCase.getCpuTimeCost();
        Integer realTimeCost = testCase.getRealTimeCost();
        if (cpuTimeCost == null) {
            cpuTimeCost = 0;
        }
        if (realTimeCost == null) {
            realTimeCost = 0;
        }
        return Math.max(cpuTimeCost, realTimeCost);
    }

    /**
     * 获取判题结果描述
     * 判题描述是对一次提交的汇总评价，以一个字符串的形式来表示，例如"ACCEPT"、"WRONG_ANSWER"等，
     * 在多个提交中，如果全ac则返回accept否则返回第一个错误描述
     *
     * @author yuzhanglong
     * @date 2020-7-31 9:48
     */
    public JudgeResultEnum countJudgeResult() {
        List<JudgeTestCaseResultDTO> testCaseResultDTOList = judgeResult.getJudgeResults();
        for (JudgeTestCaseResultDTO resultDTO : testCaseResultDTOList) {
            String message = resultDTO.getMessage();
            JudgeResultEnum condition = JudgeResultEnum.toJudgeResult(message);
            // WA 或者没有condition，我们返回WA
            if (condition == null || condition == JudgeResultEnum.WRONG_ANSWER) {
                return JudgeResultEnum.WRONG_ANSWER;
            }
            // 其他情况，我们返回第一个（不是ac）
            if (condition != JudgeResultEnum.ACCEPT) {
                return condition;
            }
        }
        // 当所有都ac了
        return JudgeResultEnum.ACCEPT;
    }

    /**
     * @author yuzhanglong
     * @description 获取内存消耗
     * @date 2020-7-31 9:38
     */
    private Integer countMaxMemory(JudgeTestCaseResultDTO testCase) {
        Integer memoryCost = testCase.getMemoryCost();
        return memoryCost == null ? 0 : memoryCost;
    }

    public JudgeResultDTO getJudgeResult() {
        return judgeResult;
    }

    public void setJudgeResult(JudgeResultDTO judgeResult) {
        this.judgeResult = judgeResult;
    }

    public Integer getTimeCost() {
        return timeCost;
    }

    public void setTimeCost(Integer timeCost) {
        this.timeCost = timeCost;
    }

    public Integer getMemoryCost() {
        return memoryCost;
    }

    public void setMemoryCost(Integer memoryCost) {
        this.memoryCost = memoryCost;
    }
}
