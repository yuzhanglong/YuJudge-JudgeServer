package com.yzl.yujudge.core.enumeration;

import java.util.ArrayList;
import java.util.List;
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
     * 语言名称字符串转化为枚举类型
     * 如果找不到，我们返回一个null
     *
     * @param language 语言的名称，例如：JAVA
     * @return LanguageEnum 该语言对应的枚举类型
     * @author yuzhanglong
     * @date 2020-7-29 13:34:29
     */
    public static LanguageEnum toLanguageType(String language) {
        return Stream.of(LanguageEnum.values())
                .filter(c -> c.toString().equals(language))
                .findAny()
                .orElse(null);
    }

    /**
     * 获取所有可以支持的语言
     *
     * @return LanguageEnum 该语言对应的枚举类型
     * @author yuzhanglong
     * @date 2020-8-24 22:00:38
     */
    public static List<String> getAllAcceptedLanguage() {
        List<String> res = new ArrayList<>();
        for (LanguageEnum value : LanguageEnum.values()) {
            res.add(value.name());
        }
        return res;
    }
}
