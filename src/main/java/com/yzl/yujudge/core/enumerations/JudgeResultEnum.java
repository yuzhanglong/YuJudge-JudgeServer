package com.yzl.yujudge.core.enumerations;


/**
 * @author yuzhanglong
 * @date 2020-7-29 14:32:29
 * @description 判题结果枚举类
 */

public enum JudgeResultEnum {
    // 程序通过这个测试样例
    ACCEPTED,

    // 答案错误
    WRONG_ANSWER,

    // 运行时错误
    RUNTIME_ERROR,

    // 时间超限
    TIME_LIMIT_EXCEEDED,

    // 内存超限
    MEMORY_LIMIT_EXCEED,

    // 输出超限
    OUTPUT_LIMIT_EXCEED,

    // 段错误
    SEGMENTATION_FAULT,

    // 浮点错误
    FLOAT_ERROR,

    // 未知错误
    UNKNOWN_ERROR,

    //编译失败
    COMPILE_ERROR;
}
