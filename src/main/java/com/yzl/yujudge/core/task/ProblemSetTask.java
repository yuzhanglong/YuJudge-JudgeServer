package com.yzl.yujudge.core.task;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yzl.yujudge.bo.ScoreBoardBO;
import com.yzl.yujudge.model.ProblemSetEntity;
import com.yzl.yujudge.repository.ProblemSetRepository;
import com.yzl.yujudge.service.ProblemSetService;
import com.yzl.yujudge.store.redis.RedisOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author yuzhanglong
 * @description 题目集相关定时任务
 * @date 2020-08-14 12:59:01
 */
@Component
public class ProblemSetTask {
    private final ProblemSetRepository problemSetRepository;
    private final ProblemSetService problemSetService;
    private final RedisOperations redisOperations;
    public static final String PROBLEM_SET_SCORE_BOARD_REDIS_SAVE_PREFIX = "problem_set_score_board";

    public ProblemSetTask(
            ProblemSetRepository problemSetRepository,
            ProblemSetService problemSetService,
            RedisOperations redisOperations) {
        this.problemSetRepository = problemSetRepository;
        this.problemSetService = problemSetService;
        this.redisOperations = redisOperations;
    }

    @Scheduled(cron = "*/5 * * * * ?")
    private void renewActiveProblemSetScoreBoard() {
        Date current = new Date();
        List<ProblemSetEntity> activeProblemSets = problemSetRepository.fineBetweenCurrentTime(current);
        for (ProblemSetEntity activeProblemSet : activeProblemSets) {
            ScoreBoardBO scoreBoardBO = problemSetService.getProblemSetScoreBoard(activeProblemSet);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.valueToTree(scoreBoardBO);
            String key = PROBLEM_SET_SCORE_BOARD_REDIS_SAVE_PREFIX + "_" + activeProblemSet.getId().toString();
            boolean isSet = redisOperations.set(key, jsonNode);
        }
    }
}
