package com.yzl.yujudge.utils;

import java.util.Date;

/**
 * @author yuzhanglong
 * @description 时间相关工具类
 * @date 2020-08-12 10:22:49
 */
public class DateTimeUtil {
    public static final int TOO_LATE = 1;
    public static final int TOO_EARLY = 2;
    public static final int GOOD = 3;

    /**
     * @param startTime 开始时间
     * @param deadline  截止时间
     * @return Integer 状态
     * TOO_LATE  太迟了 -------- 1
     * TOO_EARLY 太早了 -------- 2
     * GOOD      时间正常 ------ 3
     * @author yuzhanglong
     * @description 判断当前时间状态
     * @date 2020-08-12 10:31:17
     */
    public static Integer checkTimeCondition(Date startTime, Date deadline) {
        Date current = new Date();
        if (startTime.after(current)) {
            return TOO_EARLY;
        }
        if (deadline.before(current)) {
            return TOO_LATE;
        }
        return GOOD;
    }

}
