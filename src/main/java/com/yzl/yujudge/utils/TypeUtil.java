package com.yzl.yujudge.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类型处理工具类
 *
 * @author yuzhanglong
 * @date 2020-9-9 13:50:44
 */
public class TypeUtil {
    /**
     * object转Map
     *
     * @author yuzhanglong
     * @date 2020-9-9 13:50:47
     */
    public static <K, V> Map<K, V> castObjectToHashMap(Object obj, Class<K> classKey, Class<V> classValue) {
        Map<K, V> result = new HashMap<>(5);
        if (obj instanceof Map<?, ?>) {
            ((Map<?, ?>) obj).forEach((k, v) -> {
                result.put(classKey.cast(k), classValue.cast(v));
            });
            return result;
        }
        return null;
    }

    /**
     * object转List
     *
     * @author yuzhanglong
     * @date 2020-9-9 13:50:47
     */
    public static <T> List<T> castObjectToList(Object obj, Class<T> classKey) {
        List<T> result = new ArrayList<>(10);
        if (obj instanceof List<?>) {
            ((List<?>) obj).forEach(res -> result.add(classKey.cast(res)));
            return result;
        }
        return null;
    }
}
