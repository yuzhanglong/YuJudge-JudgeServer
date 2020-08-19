package com.yzl.yujudge.utils.comparator;


import com.yzl.yujudge.bo.JudgeHostBO;

import java.util.Comparator;

/**
 * @author yuzhanglong
 * @description 判题机状态业务对象比较类
 * @date 2020-8-18 18:47:29
 * 我们有多个判题机可供调用，
 * 需要找出当前状态下最适合的判题机，做到负载均衡
 */
public class BestJudgeHostComparator implements Comparator<JudgeHostBO> {
    @Override
    public int compare(JudgeHostBO o1, JudgeHostBO o2) {
        // 当前工作数少的优先
        if (o1.getCondition().getWorkingAmount() > o2.getCondition().getWorkingAmount()) {
            return 1;
        } else if (o1.getCondition().getWorkingAmount() < o2.getCondition().getWorkingAmount()) {
            return -1;
        } else {
            // 当前排队数少的优先
            return o1.getCondition().getQueueAmount() - o2.getCondition().getQueueAmount();
        }
    }
}
