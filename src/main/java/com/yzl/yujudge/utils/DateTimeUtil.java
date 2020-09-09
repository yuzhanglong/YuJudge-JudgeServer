package com.yzl.yujudge.utils;

import com.yzl.yujudge.core.enumeration.DateTimeFormatEnum;
import com.yzl.yujudge.core.exception.http.ForbiddenException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间相关工具类
 *
 * @author yuzhanglong
 * @date 2020-08-12 10:22:49
 */
public class DateTimeUtil {
    public static final int TOO_LATE = 1;
    public static final int TOO_EARLY = 2;
    public static final int GOOD = 3;

    /**
     * 判断当前时间是否在时间区间内
     *
     * @param startTime 开始时间
     * @param deadline  截止时间
     * @return Integer 状态
     * TOO_LATE  太迟了 -------- 1
     * TOO_EARLY 太早了 -------- 2
     * GOOD      时间正常 ------ 3
     * @author yuzhanglong
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

    /**
     * 计算两时间之间相差的分钟数
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return Long 相差的分钟数目
     * @author yuzhanglong
     * @date 2020-08-13 18:41:10
     */
    public static Long countTimeCostInMinute(Date start, Date end) {
        long mSecond = end.getTime() - start.getTime();
        return mSecond / (60 * 1000);
    }

    /**
     * 将时间字符串转换成date对象
     * 如果转化失败，我们抛出全局异常 A0008 - 错误的时间格式
     *
     * @param dateTimeString 时间信息字符串
     * @return Date 时间对象
     * @author yuzhanglong
     * @date 2020-8-20 19:21:07
     */
    public static Date formatDateTimeString(String dateTimeString) {
        SimpleDateFormat dateformat = new SimpleDateFormat(DateTimeFormatEnum.DEFAULT_DATE_FORMAT.getFormat());
        try {
            return dateformat.parse(dateTimeString);
        } catch (ParseException e) {
            throw new ForbiddenException("A0008");
        }
    }

    /**
     * 移除时间对象的时、分、秒
     *
     * @param rawDate 原始时间数据
     * @return Date 处理之后的时间数据
     * @author yuzhanglong
     * @date 2020-8-20 20:02:39
     */
    public static Date removeTimeFromDateObject(Date rawDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(rawDate);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }
}
