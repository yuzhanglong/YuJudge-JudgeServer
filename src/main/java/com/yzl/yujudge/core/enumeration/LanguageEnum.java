package com.yzl.yujudge.core.enumeration;

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

    /**
     * @param language 语言的名称，例如：JAVA
     * @return LanguageEnum 该语言对应的枚举类型
     * @author yuzhanglong
     * @date 2020-7-29 13:34:29
     * @description 语言名称字符串转化为枚举类型
     * 如果找不到，我们返回一个null
     * 另外，判断语言类型应该在dto层进行验证，
     * 而不是运用这个方法根据是否为null来判断
     */
    public static LanguageEnum toLanguageType(String language) {
        return Stream.of(LanguageEnum.values())
                .filter(c -> c.toString().equals(language))
                .findAny()
                .orElse(null);
    }
}
