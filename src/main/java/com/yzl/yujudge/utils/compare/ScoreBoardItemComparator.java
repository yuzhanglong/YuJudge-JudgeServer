package com.yzl.yujudge.utils.compare;

import com.yzl.yujudge.bo.ScoreBoardItemBO;

import java.util.Comparator;

/**
 * @author yuzhanglong
 * @date 2020-08-15 00:07:41
 * @description 记分板单支队伍比较类
 * ACM模式
 * 我们首先比较用户的ac数量，ac越多，排名越靠前，
 * 如果数量相同，罚时小的会排名靠前
 */
public class ScoreBoardItemComparator implements Comparator<ScoreBoardItemBO> {
    @Override
    public int compare(ScoreBoardItemBO o1, ScoreBoardItemBO o2) {
        int o1Ac = o1.getTotalAcAmount();
        int o2Ac = o2.getTotalAcAmount();
        long o1TimePenalty = o1.getTotalTimePenalty();
        long o2TimePenalty = o2.getTotalTimePenalty();
        if (o1Ac < o2Ac) {
            return 1;
        } else if (o1Ac > o2Ac) {
            return -1;
        } else {
            if (o1TimePenalty > o2TimePenalty) {
                return 1;
            } else if (o1TimePenalty < o2TimePenalty) {
                return -1;
            }
            return 0;
        }
    }
}