package com.yzl.yujudge.store.redis;

import com.yzl.yujudge.bo.JudgeHostBO;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @author yuzhanglong
 * @description 判题机相关缓存
 */

@Component
public class JudgeHostCache {
    public static final String JUDGE_HOST_INFO_SAVE_PREFIX = "judgeHosts";
    private final RedisOperations redisOperations;

    public JudgeHostCache(RedisOperations redisOperations) {
        this.redisOperations = redisOperations;
    }

    /**
     * @param judgeHostBOList 判题机相关业务对象
     * @author yuzhanglong
     * @description 更新判题机状态的信息, 存入redis中
     * @date 2020-08-17 14:18:59
     */
    public void setJudgeConditionCache(List<JudgeHostBO> judgeHostBOList) {
        // 删除旧数据
        redisOperations.remove(JUDGE_HOST_INFO_SAVE_PREFIX);
        // 遍历判断过的judgeHostBO
        for (JudgeHostBO judgeHostBO : judgeHostBOList) {
            boolean isSet = redisOperations.setList(
                    JUDGE_HOST_INFO_SAVE_PREFIX,
                    Collections.singletonList(judgeHostBO)
            );
        }
    }

    public List<Object> getJudgeConditionCache() {
        return redisOperations.getList(JUDGE_HOST_INFO_SAVE_PREFIX, 0, -1);
    }
}
