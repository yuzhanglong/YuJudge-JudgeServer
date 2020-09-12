package com.yzl.yujudge.store.redis;

import com.yzl.yujudge.bo.JudgeHostBO;
import com.yzl.yujudge.utils.RedisOperations;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.*;

/**
 * 判题机相关缓存
 *
 * @author yuzhanglong
 * @date 2020-8-18 21:43:34
 */

@Component
public class JudgeHostCache {
    public static final String JUDGE_HOST_INFO_REDIS_SAVE_PREFIX = "judgeHosts";
    private final RedisOperations redisOperations;
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();


    public JudgeHostCache(RedisOperations redisOperations) {
        this.redisOperations = redisOperations;
    }

    /**
     * @return List<Object> 判题机信息列表
     * @author yuzhanglong
     * @description 获取缓存中redis状态的信息
     * @date 2020-8-18 14:30:40
     */
    public List<Object> getJudgeHostsConditionListCache() {
        readWriteLock.readLock().lock();
        Map<Object, Object> res = redisOperations.getHashMap(JUDGE_HOST_INFO_REDIS_SAVE_PREFIX);
        readWriteLock.readLock().unlock();
        return new ArrayList<>(res.values());
    }


    /**
     * @return Object 判题机信息列表 / null 如果判题机信息不存在的话
     * @author yuzhanglong
     * @description 通过id 获取缓存中redis状态的信息（单个判题机）
     * @date 2020-8-18 14:32:39
     */
    public Object getJudgeHostsConditionByJudgeHostId(String key) {
        readWriteLock.readLock().lock();
        Map<Object, Object> res = redisOperations.getHashMap(JUDGE_HOST_INFO_REDIS_SAVE_PREFIX);
        readWriteLock.readLock().unlock();
        return res.get(key);
    }

    /**
     * @param judgeHostBOList 判题机相关业务对象
     * @author yuzhanglong
     * @description 更新判题机状态的信息, 存入redis中
     * @date 2020-08-17 14:18:59
     */
    public void setJudgeConditionCache(List<JudgeHostBO> judgeHostBOList) {
        // 写数据时我们不允许读，更不允许写，因此我们使用读写锁
        readWriteLock.writeLock().lock();
        // 删除旧数据
        redisOperations.remove(JUDGE_HOST_INFO_REDIS_SAVE_PREFIX);
        // 遍历判断过的judgeHostBO,并将每一项存入hashMap
        for (JudgeHostBO judgeHostBO : judgeHostBOList) {
            boolean isSet = redisOperations.setHashMap(
                    JUDGE_HOST_INFO_REDIS_SAVE_PREFIX,
                    judgeHostBO.getId().toString(),
                    judgeHostBO
            );
        }
        readWriteLock.writeLock().unlock();
    }
}