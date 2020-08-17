package com.yzl.yujudge.service;

import com.github.dozermapper.core.Mapper;
import com.yzl.yujudge.core.exception.http.NotFoundException;
import com.yzl.yujudge.dto.ProblemDTO;
import com.yzl.yujudge.dto.ProblemLimitationDTO;
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
    private final Mapper mapper;
    public static final int RECENT_PROBLEM_SEARCH_MAX_SIZE = 15;

    public ProblemService(ProblemRepository problemRepository, SolutionRepository solutionRepository, Mapper mapper) {
        this.problemRepository = problemRepository;
        this.solutionRepository = solutionRepository;
        this.mapper = mapper;
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
     * @param search     搜索关键字
     * @author yuzhanglong
     * @description 根据题目id来获取题目信息
     * @date 2020-7-18 04:57:55
     */
    public Page<JudgeProblemEntity> getProblems(Integer pageAmount, Integer size, String search) {
        Pageable pageable = PageRequest.of(pageAmount, size);
        return problemRepository.findByName(search, pageable);
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
        JudgeProblemEntity problemEntity = getProblemEntityById(problemId, false);
        problemEntity.setName(problemDTO.getName());
        problemEntity.setContent(problemDTO.getContent());
        if (problemDTO.getCharacterTags() != null) {
            problemEntity.setCharacterTags(problemDTO.getCharacterTags());
        }
        problemRepository.save(problemEntity);
    }

    /**
     * @param problemId 目标问题id
     * @author yuzhanglong
     * @description 删除一个problem
     * @date 2020-7-22
     */
    public void deleteProblem(Long problemId) {
        JudgeProblemEntity problemEntity = getProblemEntityById(problemId, false);
        problemRepository.delete(problemEntity);
    }


    /**
     * @param problemId 目标问题id
     * @return List<JudgeSolutionEntity> 一个或者多个解决方案实体类
     * @author yuzhanglong
     * @description 删除一个problem
     * @date 2020-7-22
     */
    public List<JudgeSolutionEntity> getProblemSolutions(Long problemId) {
        // 此处为空没有必要抛出异常，我们直接返回一个空数组就可以了
        return solutionRepository.findAllByPkProblem(problemId);
    }


    /**
     * @param problemId 目标问题id
     * @author yuzhanglong
     * @description 为目标problem添加一个解决方案
     * @date 2020-7-22
     */
    public void createSolution(Long problemId, SolutionDTO solution) {
        JudgeProblemEntity problemEntity = getProblemEntityById(problemId, false);
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

    /**
     * @param problemId 目标problemId
     * @author yuzhanglong
     * @description 关闭某个problem
     * @date 2020-7-26
     */
    public void closeProblem(Long problemId) {
        JudgeProblemEntity problemEntity = getProblemEntityById(problemId, false);
        Boolean isClosed = problemEntity.getClosed();
        problemEntity.setClosed(!isClosed);
        problemRepository.save(problemEntity);
    }

    /**
     * @param problemId 目标problemId
     * @author yuzhanglong
     * @description 修改Problem限制
     * @date 2020-7-26 17:54:45
     */
    public void setLimitation(Long problemId, ProblemLimitationDTO limitation) {
        JudgeProblemEntity problemEntity = getProblemEntityById(problemId, false);
        problemEntity.setTimeLimit(limitation.getTimeLimit());
        problemEntity.setCpuTimeLimit(limitation.getCpuTimeLimit());
        problemEntity.setOutputLimit(limitation.getOutputLimit());
        problemEntity.setMemoryLimit(limitation.getMemoryLimit());
        problemRepository.save(problemEntity);
    }

    public void setProblemBasicInfo(Long problemId, ProblemDTO problemDTO) {
        JudgeProblemEntity problemEntity = getProblemEntityById(problemId, false);
    }

    /**
     * @param problemId  目标problemId
     * @param isNullable 是否允许实体对象是否为空
     * @author yuzhanglong
     * @description 根据problemId，获取problem实体对象
     * 如果不允许实体为空的情况下，实体为空
     * 那么我们会抛出一个全局异常B0002(problem不存在)
     * @date 2020-7-26 18:07:44
     */
    private JudgeProblemEntity getProblemEntityById(Long problemId, Boolean isNullable) {
        JudgeProblemEntity problemEntity = problemRepository.findOneById(problemId);
        if (!isNullable && problemEntity == null) {
            throw new NotFoundException("B0002");
        }
        return problemEntity;
    }

    /**
     * @param size 需要获取problem的数量
     * @author yuzhanglong
     * @description 传入你需要获取的数量获取最近创建的problems，
     * 实际上是利用分页机制加上createTime的排序得出
     * 注意: 最多获取十五条，如果size大于15，则我们只去最近的十五条记录
     * @date 2020-08-06 20:55:11
     */
    public List<JudgeProblemEntity> getRecentProblem(Integer size) {
        int finalSize = size > RECENT_PROBLEM_SEARCH_MAX_SIZE ? RECENT_PROBLEM_SEARCH_MAX_SIZE : size;
        Pageable pageable = PageRequest.of(0, finalSize);
        return problemRepository.findByOrderByCreateTimeDesc(pageable);
    }
}