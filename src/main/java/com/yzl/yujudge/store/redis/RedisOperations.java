package com.yzl.yujudge.store.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author yuzhanglong
 * @description redis操作集类
 * @date 2020-08-03 20:44:55
 */

@Component
public class RedisOperations {
    private final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * @param key   键
     * @param value 值
     * @author yuzhanglong
     * @description 设置key-value对
     * @date 2020-08-03 20:49:41
     */

    public Boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param key 键
     * @author yuzhanglong
     * @description 获取key对应的value
     * @date 2020-08-03 21:18:38
     */
    public Object get(String key) {
        return key != null ? redisTemplate.opsForValue().get(key) : null;
    }


    /**
     * @param key  键
     * @param time 时间量
     * @author yuzhanglong
     * @description 设置key-value对
     * @date 2020-08-03 20:49:41
     */

    public Boolean expireKey(String key, Long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, DEFAULT_TIME_UNIT);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * @param key 需要获取过期时间的key
     * @author yuzhanglong
     * @description 获取某个key的过期时间
     * @date 2020-08-03 20:58:44
     */

    public Long getExpireTime(String key) {
        return redisTemplate.getExpire(key, DEFAULT_TIME_UNIT);
    }


    /**
     * @author yuzhanglong
     * @description 设置key-value对，同时为其设置了时间限制
     * @date 2020-08-03 21:03:07
     */
    public Boolean set(String key, Object value, Long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, DEFAULT_TIME_UNIT);
            } else {
                boolean isSet = set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param keys 需要删除的key(s)
     * @author yuzhanglong
     * @description 删除一个或者多个key
     * @date 2020-08-03 22:15:05
     */
    public void remove(String... keys) {
        if (keys != null && keys.length > 0) {
            for (String s : keys) {
                redisTemplate.delete(s);
            }
        }
    }
}
