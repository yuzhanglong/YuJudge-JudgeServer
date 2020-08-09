package com.yzl.yujudge.service;


import com.yzl.yujudge.core.exception.http.NotFoundException;
import com.yzl.yujudge.model.JudgeProblemEntity;
import com.yzl.yujudge.model.ProblemSetEntity;
import com.yzl.yujudge.repository.ProblemSetRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author yuzhanglong
 * @description problemSet的业务逻辑层
 * @date 2020-08-08 21:59:56
 */
@Service
public class ProblemSetService {
    private final ProblemSetRepository problemSetRepository;


    public ProblemSetService(ProblemSetRepository problemSetRepository) {
        this.problemSetRepository = problemSetRepository;
    }

    /**
     * @param isBeforeDeadline 限定截止时间，只选出活跃的题目集合
     * @param count            数量
     * @param start            页码
     * @return ProblemSetEntity 的分页对象
     * @author yuzhanglong
     * @date 2020-7-29 00:30:19
     * @description 题目集相关控制层
     */
    public Page<ProblemSetEntity> getProblemSetInfo(Integer start, Integer count, Boolean isBeforeDeadline) {
        Pageable pageable = PageRequest.of(start, count);
        if (!isBeforeDeadline) {
            return problemSetRepository.findByOrderByCreateTimeDesc(pageable);
        } else {
            Date current = new Date();
            return problemSetRepository.findBetweenCurrentTime(current, pageable);
        }
    }

    /**
     * @param problemSetId 题目集id
     * @return ProblemSetEntity 实体对象
     * @author yuzhanglong
     * @date 2020-08-09 11:55:25
     * @description 根据ID 获取题目集
     */
    public List<JudgeProblemEntity> getProblemSetProblems(Long problemSetId) {
        ProblemSetEntity problemSetEntity = problemSetRepository.findOneById(problemSetId);
        if (problemSetEntity == null) {
            throw new NotFoundException("B0011");
        }
        return problemSetEntity.getProblems();
    }
}