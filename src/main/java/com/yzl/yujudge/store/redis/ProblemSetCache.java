package com.yzl.yujudge.store.redis;

import com.yzl.yujudge.bo.ScoreBoardBO;
import com.yzl.yujudge.utils.RedisOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 题目集相关缓存操作
 *
 * @author yuzhanglong
 * @date 2020-08-14 18:35:43
 */

@Component
public class ProblemSetCache {
    public static final String PROBLEM_SET_SCORE_BOARD_REDIS_SAVE_PREFIX = "problem_set_score_board";
    private final RedisOperations redisOperations;
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public ProblemSetCache(RedisOperations redisOperations) {
        this.redisOperations = redisOperations;
    }

    /**
     * 添加题目集记分板缓存
     *
     * @param problemSetId   题目集id
     * @param scoreBoardInfo 记分板信息
     * @author yuzhanglong
     * @date 2020-08-15 00:10:50
     * @description
     */
    public void setProblemSetScoreBoardCache(ScoreBoardBO scoreBoardInfo, String problemSetId) {
        // 生成key
        String key = PROBLEM_SET_SCORE_BOARD_REDIS_SAVE_PREFIX + "_" + problemSetId;
        // 写数据时我们不允许读，更不允许写，因此我们使用读写锁
        readWriteLock.writeLock().lock();
        redisOperations.set(key, scoreBoardInfo);
        readWriteLock.writeLock().unlock();
    }

    /**
     * 获取题目集记分板缓存
     *
     * @param problemSetId 题目集id
     * @author yuzhanglong
     * @date 2020-08-15 00:11:42
     */
    public Object getProblemSetScoreBoardCache(Long problemSetId) {
        readWriteLock.readLock().lock();
        String key = PROBLEM_SET_SCORE_BOARD_REDIS_SAVE_PREFIX + "_" + problemSetId;
        readWriteLock.readLock().unlock();
        return redisOperations.get(key);
    }
}