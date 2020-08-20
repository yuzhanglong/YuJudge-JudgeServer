package com.yzl.yujudge.core.enumeration;

/**
 * 日期时间格式化模板字符串枚举类
 *
 * @author yuzhanglong
 */

public enum DateTimeFormatEnum {

    // 默认模板，也是前后端交互使用的模板
    DEFAULT_DATE_FORMAT("yyyy-MM-dd HH:mm:ss"),

    // 只有日期的模板
    ONLY_DATE("yyyy-MM-dd");

    private final String format;

    DateTimeFormatEnum(String s) {
        this.format = s;
    }

    public String getFormat() {
        return format;
    }

    @Override
    public String toString() {
        return "DateTimeFormatEnum{" +
                "format='" + format + '\'' +
                '}';
    }
}
