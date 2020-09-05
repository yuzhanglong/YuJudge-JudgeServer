package com.yzl.yujudge.service;

import com.github.dozermapper.core.Mapper;
import com.yzl.yujudge.core.exception.http.NotFoundException;
import com.yzl.yujudge.dto.ProblemDTO;
import com.yzl.yujudge.dto.ProblemLimitationDTO;
import com.yzl.yujudge.dto.SolutionDTO;
import com.yzl.yujudge.model.JudgeProblemEntity;
import com.yzl.yujudge.model.JudgeSolutionEntity;
import com.yzl.yujudge.model.UserEntity;
import com.yzl.yujudge.repository.ProblemRepository;
import com.yzl.yujudge.repository.SolutionRepository;
import com.yzl.yujudge.repository.UserRepository;
import com.yzl.yujudge.utils.ToEntityUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author yuzhanglong
 * @description 处理题目信息相关的业务逻辑
 * @date 2020-7-18 04:55:28
 */

@Service
public class ProblemService {
    private final ProblemRepository problemRepository;
    private final UserRepository userRepository;
    private final SolutionRepository solutionRepository;
    private final Mapper mapper;
    public static final int RECENT_PROBLEM_SEARCH_MAX_SIZE = 15;
    public static final int TIME_LIMIT_DEFAULT = 1000 * 5;
    public static final int OUTPUT_LIMIT_DEFAULT = 100000;
    public static final String PROBLEM_CONTENT_DEFAULT = "您可以修改题目内容";

    public ProblemService(
            ProblemRepository problemRepository,
            UserRepository userRepository,
            SolutionRepository solutionRepository,
            Mapper mapper) {
        this.problemRepository = problemRepository;
        this.userRepository = userRepository;
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
     * 根据题目id来获取题目信息
     *
     * @param pageAmount 页码数量
     * @param size       数据条数
     * @param search     搜索关键字
     * @author yuzhanglong
     * @date 2020-7-18 04:57:55
     */
    public Page<JudgeProblemEntity> getProblems(Integer pageAmount, Integer size, String search) {
        Pageable pageable = PageRequest.of(pageAmount, size);
        return problemRepository.findByName(search, pageable);
    }


    /**
     * 创建一个problem
     *
     * @param problemInfo ProblemDTO对象（问题信息）
     * @author yuzhanglong
     * @date 2020-7-20 23:23:57
     */
    public void createProblem(ProblemDTO problemInfo) {
        // problem实体类
        JudgeProblemEntity problem = ToEntityUtil.problemDtoToProblemEntity(problemInfo);
        problem.setCpuTimeLimit(TIME_LIMIT_DEFAULT);
        problem.setTimeLimit(TIME_LIMIT_DEFAULT);
        problem.setCharacterTags(new ArrayList<>());
        problem.setOutputLimit(OUTPUT_LIMIT_DEFAULT);
        problem.setContent(PROBLEM_CONTENT_DEFAULT);
        problemRepository.save(problem);
    }

    /**
     * 编辑一个problem
     *
     * @param problemId  要修改的问题
     * @param problemDTO 修改的问题信息
     * @author yuzhanglong
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
     * 删除一个problem
     *
     * @param problemId 目标问题id
     * @author yuzhanglong
     * @date 2020-7-22
     */
    public void deleteProblem(Long problemId) {
        JudgeProblemEntity problemEntity = getProblemEntityById(problemId, false);
        problemRepository.delete(problemEntity);
    }


    /**
     * 删除一个problem
     *
     * @param problemId 目标问题id
     * @return List<JudgeSolutionEntity> 一个或者多个解决方案实体类
     * @author yuzhanglong
     * @date 2020-7-22
     */
    public List<JudgeSolutionEntity> getProblemSolutions(Long problemId) {
        // 此处为空没有必要抛出异常，我们直接返回一个空数组就可以了
        return solutionRepository.findAllByPkProblem(problemId);
    }


    /**
     * 为目标problem添加一个解决方案
     *
     * @param problemId 目标问题id
     * @author yuzhanglong
     * @date 2020-7-22
     */
    public void createSolution(Long problemId, SolutionDTO solution) {
        JudgeProblemEntity problemEntity = getProblemEntityById(problemId, false);
        JudgeSolutionEntity solutionEntity = mapper.map(solution, JudgeSolutionEntity.class);
        problemEntity.getJudgeSolutionEntityList().add(solutionEntity);
        solutionRepository.save(solutionEntity);
        problemRepository.save(problemEntity);
    }


    /**
     * 删除某个solution
     *
     * @param solutionId 目标solutionId
     * @author yuzhanglong
     * @date 2020-7-22
     */
    public void deleteSolution(Long solutionId) {
        JudgeSolutionEntity solutionEntity = solutionRepository.findOneById(solutionId);
        if (solutionEntity == null) {
            throw new NotFoundException("B0003");
        }
        solutionRepository.softDeleteById(solutionEntity.getId());
    }

    /**
     * 关闭某个problem
     *
     * @param problemId 目标problemId
     * @author yuzhanglong
     * @date 2020-7-26
     */
    public void closeProblem(Long problemId) {
        JudgeProblemEntity problemEntity = getProblemEntityById(problemId, false);
        Boolean isClosed = problemEntity.getClosed();
        problemEntity.setClosed(!isClosed);
        problemRepository.save(problemEntity);
    }

    /**
     * 修改Problem限制
     *
     * @param problemId 目标problemId
     * @author yuzhanglong
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

    /**
     * 根据problemId，获取problem实体对象
     * 如果不允许实体为空的情况下，实体为空
     * 那么我们会抛出一个全局异常B0002(problem不存在)
     *
     * @param problemId  目标problemId
     * @param isNullable 是否允许实体对象是否为空
     * @author yuzhanglong
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
     * 传入你需要获取的数量获取最近创建的problems，
     * 实际上是利用分页机制加上createTime的排序得出
     * 注意: 最多获取十五条，如果size大于15，则我们只去最近的十五条记录
     *
     * @param size 需要获取problem的数量
     * @author yuzhanglong
     * @date 2020-08-06 20:55:11
     */
    public List<JudgeProblemEntity> getRecentProblem(Integer size) {
        int finalSize = size > RECENT_PROBLEM_SEARCH_MAX_SIZE ? RECENT_PROBLEM_SEARCH_MAX_SIZE : size;
        Pageable pageable = PageRequest.of(0, finalSize);
        return problemRepository.findByOrderByCreateTimeDesc(pageable);
    }

    /**
     * 根据名称获取problem
     *
     * @param name 名称
     * @return List JudgeProblemEntity 的分页对象
     * @author yuzhanglong
     * @date 2020-9-4 21:51:26
     */
    public JudgeProblemEntity getProblemByName(String name) {
        return problemRepository.findTop1ByName(name);
    }

    /**
     * 获取用户通过的问题(id集合)
     *
     * @param userId 用户ID
     * @author yuzhanglong
     * @date 2020-9-5 15:55:58
     */
    public List<Map<String, Object>> getUserAcceptProblems(Long userId) {
        UserEntity userEntity = userRepository.findOneById(userId);
        if (userEntity == null) {
            throw new NotFoundException("B0006");
        }
        Set<List<Object>> res = problemRepository.getUserAcProblemInfo(userEntity);
        return publishProblemInfo(res);
    }

    /**
     * 获取用户尝试过的问题(没通过)
     *
     * @param userId 用户ID
     * @author yuzhanglong
     * @date 2020-9-5 15:55:58
     */
    public List<Map<String, Object>> getUserTriedProblems(Long userId) {
        UserEntity userEntity = userRepository.findOneById(userId);
        if (userEntity == null) {
            throw new NotFoundException("B0006");
        }
        Set<List<Object>> res = problemRepository.getUserTriedProblemInfo(userEntity);
        return publishProblemInfo(res);
    }

    /**
     * 处理问题统计信息
     *
     * @param raw 表中查询的未处理的数据
     * @author yuzhanglong
     * @date 2020-9-5 17:49:39
     */
    public List<Map<String, Object>> publishProblemInfo(Set<List<Object>> raw) {
        List<Map<String, Object>> result = new ArrayList<>();
        for (List<Object> re : raw) {
            Map<String, Object> tmp = new HashMap<>(2);
            tmp.put("problemId", re.get(0));
            tmp.put("name", re.get(1));
            result.add(tmp);
        }
        return result;
    }
}