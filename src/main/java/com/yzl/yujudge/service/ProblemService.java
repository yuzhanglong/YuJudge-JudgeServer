package com.yzl.yujudge.service;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.yzl.yujudge.core.exception.http.NotFoundException;
import com.yzl.yujudge.dto.ProblemDTO;
import com.yzl.yujudge.dto.SolutionDTO;
import com.yzl.yujudge.model.JudgeProblemEntity;
import com.yzl.yujudge.model.JudgeSolutionEntity;
import com.yzl.yujudge.repository.ProblemRepository;
import com.yzl.yujudge.repository.SolutionRepository;
import com.yzl.yujudge.utils.ToEntityUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yuzhanglong
 * @description 处理题目信息相关的业务逻辑
 * @date 2020-7-18 04:55:28
 */

@Service
public class ProblemService {
    private final ProblemRepository problemRepository;
    private final SolutionRepository solutionRepository;

    public ProblemService(ProblemRepository problemRepository, SolutionRepository solutionRepository) {
        this.problemRepository = problemRepository;
        this.solutionRepository = solutionRepository;
    }

    /**
     * @param problemId 问题id
     * @author yuzhanglong
     * @description 根据题目id来获取题目信息
     * @date 2020-7-18 04:57:55
     */
    public JudgeProblemEntity getProblemInfoById(Long problemId) {
        JudgeProblemEntity problem = problemRepository.findOneById(problemId);
        if (problem == null) {
            throw new NotFoundException("B0002");
        }
        return problem;
    }

    /**
     * @param pageAmount 页码数量
     * @param size       数据条数
     * @author yuzhanglong
     * @description 根据题目id来获取题目信息
     * @date 2020-7-18 04:57:55
     */
    public Page<JudgeProblemEntity> getProblems(Integer pageAmount, Integer size) {
        Pageable pageable = PageRequest.of(pageAmount, size);
        return problemRepository.findAll(pageable);
    }


    /**
     * @param problemInfo ProblemDTO对象（问题信息）
     * @author yuzhanglong
     * @description 创建一个problem
     * @date 2020-7-20 23:23:57
     */
    public void createProblem(ProblemDTO problemInfo) {
        // problem实体类
        JudgeProblemEntity problem = ToEntityUtil.problemDtoToProblemEntity(problemInfo);
        problemRepository.save(problem);
    }

    /**
     * @param problemId  要修改的问题
     * @param problemDTO 修改的问题信息
     * @author yuzhanglong
     * @description 编辑一个problem
     * @date 2020-7-20 23:29:23
     */
    public void editProblem(Long problemId, ProblemDTO problemDTO) {
        JudgeProblemEntity problem = problemRepository.findOneById(problemId);
        if (problem == null) {
            throw new NotFoundException("B0002");
        }
        List<JudgeSolutionEntity> oldSolutions = problem.getJudgeSolutionEntityList();
        solutionRepository.deleteAll(oldSolutions);
        problemRepository.save(problem);
    }

    /**
     * @param problemId 目标问题id
     * @author yuzhanglong
     * @description 删除一个problem
     * @date 2020-7-22
     */
    public void deleteProblem(Long problemId) {
        JudgeProblemEntity problem = problemRepository.findOneById(problemId);
        if (problem == null) {
            throw new NotFoundException("B0002");
        }
        problemRepository.delete(problem);
    }


    /**
     * @param problemId 目标问题id
     * @return List<JudgeSolutionEntity> 一个或者多个解决方案实体类
     * @author yuzhanglong
     * @description 删除一个problem
     * @date 2020-7-22
     */
    public List<JudgeSolutionEntity> getProblemSolutions(Long problemId) {
        List<JudgeSolutionEntity> solutionEntity = solutionRepository.findAllByPkProblem(problemId);
        // 此处为空没有必要抛出异常，我们直接返回一个空数组就可以了
        return solutionEntity;
    }


    /**
     * @param problemId 目标问题id
     * @author yuzhanglong
     * @description 为目标problem添加一个解决方案
     * @date 2020-7-22
     */
    public void createSolution(Long problemId, SolutionDTO solution) {
        JudgeProblemEntity problemEntity = problemRepository.findOneById(problemId);
        if (problemEntity == null) {
            throw new NotFoundException("B0002");
        }
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        JudgeSolutionEntity solutionEntity = mapper.map(solution, JudgeSolutionEntity.class);
        problemEntity.getJudgeSolutionEntityList().add(solutionEntity);
        solutionRepository.save(solutionEntity);
    }


    /**
     * @param solutionId 目标solutionId
     * @author yuzhanglong
     * @description 删除某个solution
     * @date 2020-7-22
     */
    public void deleteSolution(Long solutionId) {
        JudgeSolutionEntity solutionEntity = solutionRepository.findOneById(solutionId);
        if (solutionId == null) {
            throw new NotFoundException("B0003");
        }
        solutionRepository.delete(solutionEntity);
    }
}
