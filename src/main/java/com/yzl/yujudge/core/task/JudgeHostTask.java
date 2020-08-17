package com.yzl.yujudge.core.task;

import com.yzl.yujudge.bo.JudgeHostBO;
import com.yzl.yujudge.model.JudgeHostEntity;
import com.yzl.yujudge.repository.JudgeHostRepository;
import com.yzl.yujudge.service.JudgeHostService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yuzhanglong
 * @description 判题机相关任务
 * @date 2020-08-17 12:20:15
 */

@Component
public class JudgeHostTask {
    private final JudgeHostRepository judgeHostRepository;
    private final JudgeHostService judgeHostService;

    public JudgeHostTask(
            JudgeHostRepository judgeHostRepository,
            JudgeHostService judgeHostService) {
        this.judgeHostRepository = judgeHostRepository;
        this.judgeHostService = judgeHostService;
    }

    /**
     * @author yuzhanglong
     * @description 更新判题机状态的信息, 最终这些信息会被存入redis中
     * @date 2020-08-17 12:25:40
     */
    @Scheduled(fixedDelay = 5 * 1_000)
    public void testJudgeHostConnection() {
        // 从数据库中找出所有判题机信息
        List<JudgeHostEntity> judgeHostEntities = judgeHostRepository.findAll();
        List<JudgeHostBO> judgeHostBOList = judgeHostService.inspectJudgeHosts(judgeHostEntities);
        judgeHostService.setJudgeConditionCache(judgeHostBOList);
    }
}
