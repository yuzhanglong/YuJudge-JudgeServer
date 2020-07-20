package com.yzl.yujudge.service;

import com.yzl.yujudge.core.exception.http.NotFoundException;
import com.yzl.yujudge.model.JudgeProblemEntity;
import com.yzl.yujudge.repository.ProblemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author yuzhanglong
 * @description 处理题目信息相关的业务逻辑
 * @date 2020-7-18 04:55:28
 */

@Service
public class ProblemService {
    private final ProblemRepository problemRepository;

    public ProblemService(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    /**
     * @param problemId 问题id
     * @author yuzhanglong
     * @description 根据题目id来获取题目信息
     * @date 2020-7-18 04:57:55
     */
    public JudgeProblemEntity getProblemInfoById(Long problemId) {
        JudgeProblemEntity problem = problemRepository.findOneById(problemId);
        if(problem == null){
            throw new NotFoundException("B0002");
        }
        return problem;
    }

    /**
     * @param pageAmount 页码数量
     * @param size 数据条数
     * @author yuzhanglong
     * @description 根据题目id来获取题目信息
     * @date 2020-7-18 04:57:55
     */
    public Page<JudgeProblemEntity> getProblems(Integer pageAmount, Integer size){
        Pageable pageable = PageRequest.of(pageAmount, size);
        return problemRepository.findAll(pageable);
    }
}
