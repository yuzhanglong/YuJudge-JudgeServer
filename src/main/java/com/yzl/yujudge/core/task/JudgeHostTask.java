package com.yzl.yujudge.core.task;

import com.yzl.yujudge.service.JudgeHostService;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

/**
 * 判题机相关任务
 *
 * @author yuzhanglong
 * @date 2020-08-17 12:20:15
 */

@Component
public class JudgeHostTask {
    private final JudgeHostService judgeHostService;
    private static final int TASK_POOL_SIZE = 20;

    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(TASK_POOL_SIZE);
        return taskScheduler;
    }

    public JudgeHostTask(
            JudgeHostService judgeHostService) {
        this.judgeHostService = judgeHostService;
    }

    /**
     * @author yuzhanglong
     * @description 更新判题机状态的信息, 最终这些信息会被存入redis中
     * @date 2020-08-17 12:25:40
     */
    @Scheduled(fixedDelay = 2000)
    public void testJudgeHostConnection() {
        judgeHostService.setJudgeConditionCache();
    }
}
