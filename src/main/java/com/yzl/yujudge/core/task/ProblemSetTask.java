package com.yzl.yujudge.core.task;

import com.yzl.yujudge.bo.ScoreBoardBO;
import com.yzl.yujudge.model.ProblemSetEntity;
import com.yzl.yujudge.repository.ProblemSetRepository;
import com.yzl.yujudge.service.ProblemSetService;
import com.yzl.yujudge.store.redis.ProblemSetCache;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 题目集相关定时任务
 *
 * @author yuzhanglong
 * @date 2020-08-14 12:59:01
 */
@Component
public class ProblemSetTask {
    private final ProblemSetRepository problemSetRepository;
    private final ProblemSetService problemSetService;
    private final ProblemSetCache problemSetCache;

    public ProblemSetTask(
            ProblemSetRepository problemSetRepository,
            ProblemSetService problemSetService,
            ProblemSetCache problemSetCache) {
        this.problemSetRepository = problemSetRepository;
        this.problemSetService = problemSetService;
        this.problemSetCache = problemSetCache;
    }

    /**
     * 更新活跃题目集记分板的信息
     *
     * @author yuzhanglong
     * @date 2020-08-14 14:32:12
     */
    @Scheduled(fixedDelay = 5 * 1_000)
    public void renewActiveProblemSetScoreBoard() {
        //TODO: 这里采用定时任务获取计分板是一种不优雅的方式（虽然减少了大量的连接查询）需要优化
        Date current = new Date();
        List<ProblemSetEntity> activeProblemSets = problemSetRepository.findBetweenCurrentTime(current);
        for (ProblemSetEntity activeProblemSet : activeProblemSets) {
            ScoreBoardBO scoreBoardBO = problemSetService.getProblemSetScoreBoard(activeProblemSet);
            problemSetCache.setProblemSetScoreBoardCache(scoreBoardBO, activeProblemSet.getId().toString());
        }
    }
}
