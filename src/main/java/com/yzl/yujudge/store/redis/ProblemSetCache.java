package com.yzl.yujudge.store.redis;

import com.yzl.yujudge.bo.ScoreBoardBO;
import org.springframework.stereotype.Component;

/**
 * @author yuzhanglong
 * @description 题目集相关缓存操作
 * @date 2020-08-14 18:35:43
 */

@Component
public class ProblemSetCache {
    public static final String PROBLEM_SET_SCORE_BOARD_REDIS_SAVE_PREFIX = "problem_set_score_board";
    private final RedisOperations redisOperations;

    public ProblemSetCache(RedisOperations redisOperations) {
        this.redisOperations = redisOperations;
    }

    /**
     * @param problemSetId   题目集id
     * @param scoreBoardInfo 记分板信息
     * @author yuzhanglong
     * @date 2020-08-15 00:10:50
     * @description 添加题目集记分板缓存
     */
    public void setProblemSetScoreBoardCache(ScoreBoardBO scoreBoardInfo, String problemSetId) {
        // 生成key
        String key = PROBLEM_SET_SCORE_BOARD_REDIS_SAVE_PREFIX + "_" + problemSetId;
        redisOperations.set(key, scoreBoardInfo);
    }

    /**
     * @param problemSetId 题目集id
     * @author yuzhanglong
     * @date 2020-08-15 00:11:42
     * @description 添加题目集记分板缓存
     */
    public Object getProblemSetScoreBoardCache(Long problemSetId) {
        String key = PROBLEM_SET_SCORE_BOARD_REDIS_SAVE_PREFIX + "_" + problemSetId;
        return redisOperations.get(key);
    }
}