package com.yzl.yujudge.store.redis;

import com.yzl.yujudge.utils.RedisOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 提交相关缓存
 *
 * @author yuzhanglong
 * @date 2020-9-12 15:08:12
 */

@Component
public class SubmissionCache {
    public static final String SUBMISSION_FREQUENCY_CONTROL_PREFIX = "user_submission_control";
    private final RedisOperations redisOperations;
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public SubmissionCache(RedisOperations redisOperations) {
        this.redisOperations = redisOperations;
    }

    /**
     * 添加一个用户提交记录
     *
     * @param userId 用户id
     * @author yuzhanglong
     * @date 2020-9-12 15:12:06
     */
    public void setUserSubmissionNote(Long userId, Long timeInSecond) {
        readWriteLock.writeLock().lock();
        // 增加一条用户记录, 并附带上过期时间
        redisOperations.set(SUBMISSION_FREQUENCY_CONTROL_PREFIX + "_" + userId, "Yes", timeInSecond);
        readWriteLock.writeLock().unlock();
    }

    /**
     * 获取用户提交记录
     *
     * @param userId 用户id
     * @author yuzhanglong
     * @date 2020-9-12 16:01:34
     */
    public Object getUserFromSubmissionNote(Long userId) {
        readWriteLock.readLock().lock();
        Object data = redisOperations.get(SUBMISSION_FREQUENCY_CONTROL_PREFIX + "_" + userId);
        readWriteLock.readLock().unlock();
        return data;
    }
}
