package com.yzl.yujudge.utils;

import com.yzl.yujudge.dto.JudgeResultDTO;
import com.yzl.yujudge.dto.JudgeTestCaseResultDTO;

import java.util.List;

/**
 * @author yuzhanglong
 * @description 判题结果数据处理的工具类
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
     * @author yuzhanglong
     * @description 执行计算方案
     * @date 2020-7-30 13:19
     */
    public void executeCalculate() {
        countMaxTimeAndMaxMemoryCost();
    }

    /**
     * @author yuzhanglong
     * @description 获取最大的时间消耗和内存消耗
     * @date 2020-7-30 13:18
     */
    private void countMaxTimeAndMaxMemoryCost() {
        List<JudgeTestCaseResultDTO> testCaseResultDTOList = judgeResult.getJudgeResults();
        int time = 0;
        int memory = 0;
        for (JudgeTestCaseResultDTO testCase : testCaseResultDTOList) {
            int maxTime = Math.max(testCase.getCpuTimeCost(), testCase.getRealTimeCost());
            int maxMemory = testCase.getMemoryCost();
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
