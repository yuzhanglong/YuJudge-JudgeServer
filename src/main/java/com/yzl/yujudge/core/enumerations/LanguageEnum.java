package com.yzl.yujudge.core.enumerations;

import java.util.stream.Stream;

/**
 * @author yuzhanglong
 */

public enum LanguageEnum {
    // for python
    PYTHON,
    // for java
    JAVA,
    // for c
    C,
    // for cpp
    C_PLUS_PLUS;

    /**
     * @param languageName 语言的名称，例如：JAVA
     * @return Boolean 是否支持传入的语言
     * @author yuzhanglong
     * @date 2020-7-26 18:34:32
     * @description 判断是否支持某个语言
     */
    public static Boolean isLanguageAccepted(String languageName) {
        return toLanguageType(languageName) != null;
    }

    public static LanguageEnum toLanguageType(String language) {
        return Stream.of(LanguageEnum.values())
                .filter(c -> c.toString().equals(language))
                .findAny()
                .orElse(null);
    }
}
