package com.yzl.yujudge.service;


import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.yzl.yujudge.core.authorization.UserHolder;
import com.yzl.yujudge.core.enumeration.ProblemSetConditionEnum;
import com.yzl.yujudge.core.exception.http.NotFoundException;
import com.yzl.yujudge.dto.ProblemSetDTO;
import com.yzl.yujudge.model.JudgeProblemEntity;
import com.yzl.yujudge.model.ProblemSetEntity;
import com.yzl.yujudge.model.UserEntity;
import com.yzl.yujudge.repository.ProblemRepository;
import com.yzl.yujudge.repository.ProblemSetRepository;
import com.yzl.yujudge.repository.UserRepository;
import com.yzl.yujudge.utils.DateTimeUtil;
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
    private final UserRepository userRepository;
    private final ProblemRepository problemRepository;


    public ProblemSetService(ProblemSetRepository problemSetRepository, UserRepository userRepository, ProblemRepository problemRepository) {
        this.problemSetRepository = problemSetRepository;
        this.userRepository = userRepository;
        this.problemRepository = problemRepository;
    }

    /**
     * @param isBeforeDeadline 限定截止时间，只选出活跃的题目集合
     * @param count            数量
     * @param start            页码
     * @param search           关键词限制(搜索)
     * @return ProblemSetEntity 的分页对象
     * @author yuzhanglong
     * @date 2020-7-29 00:30:19
     * @description 题目集相关控制层
     */
    public Page<ProblemSetEntity> getProblemSetInfo(
            Integer start,
            Integer count,
            String search,
            Boolean isBeforeDeadline) {
        Pageable pageable = PageRequest.of(start, count);
        if (!isBeforeDeadline) {
            return problemSetRepository.findByName(search, pageable);
        } else {
            Date current = new Date();
            return problemSetRepository.findByNameBetweenCurrentTime(current, search, pageable);
        }
    }

    /**
     * @param problemSetId 题目集id
     * @param start        页码
     * @param count        每页的个数
     * @return ProblemSetEntity 实体对象
     * @author yuzhanglong
     * @date 2020-08-10 18:49:35
     * @description 根据ID 获取题目集
     */
    public Page<JudgeProblemEntity> getProblemSetProblems(
            Integer start,
            Integer count,
            Long problemSetId) {
        ProblemSetEntity problemSetEntity = problemSetRepository.findOneById(problemSetId);
        if (problemSetEntity == null) {
            throw new NotFoundException("B0011");
        }
        Pageable pageable = PageRequest.of(start, count);
        return problemRepository.findAllByProblemSetEntityList(problemSetEntity, pageable);
    }


    /**
     * @param problemSetDTO 题目集的数据传输对象
     * @author yuzhanglong
     * @date 2020-08-09 15:51:18
     * @description 创建一个题目集
     */
    public void createProblemSet(ProblemSetDTO problemSetDTO) {
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        ProblemSetEntity problemSetEntity = mapper.map(problemSetDTO, ProblemSetEntity.class);
        UserEntity user = userRepository.findOneById(UserHolder.getUserId());
        if (user == null) {
            throw new NotFoundException("A0001");
        }
        problemSetEntity.setCreator(user);
        problemSetRepository.save(problemSetEntity);
    }


    /**
     * @param problemSetId 目标题目集id
     * @param problems     将要被田间进题目集的problem数组(id的形式)
     * @author yuzhanglong
     * @date 2020-08-10 23:11:27
     * @description 题目id数组去重，防止重复添加
     */
    public void updateProblemSetProblem(Long problemSetId, List<Long> problems) {
        System.out.println(problems);
        ProblemSetEntity problemSetEntity = problemSetRepository.findOneById(problemSetId);
        if (problemSetEntity == null) {
            throw new NotFoundException("B0011");
        }
        List<Long> ps = cutProblemIdsList(problems, problemSetEntity.getProblems());
        System.out.println(ps);
        List<JudgeProblemEntity> problemEntityList = problemRepository.findAllById(ps);
        problemSetEntity.getProblems().addAll(problemEntityList);
        problemSetRepository.save(problemSetEntity);
    }


    /**
     * @param problems           用户传入的题目id数组
     * @param problemSetProblems 实际题目集中含有的problems
     * @return List<Long> 去重之后的数组
     * @author yuzhanglong
     * @date 2020-08-10 23:09:36
     * @description 题目id数组去重，防止重复添加
     */
    private List<Long> cutProblemIdsList(List<Long> problems, List<JudgeProblemEntity> problemSetProblems) {
        problemSetProblems.forEach(res -> {
            Long isContainProblemId = res.getId();
            problems.remove(isContainProblemId);
        });
        return problems;
    }


    /**
     * @param problemId    要从题目集中移除的问题id
     * @param problemSetId 操作的题目集id
     * @author yuzhanglong
     * @date 2020-08-10 23:06:09
     * @description 从题目集中移除某个问题(不删除问题)
     * 如果这个题目集中没有这个问题，
     * 则数据库内容不会发生改变，
     * 并且我们会抛出一个全局异常 编号为 B0012
     */
    public void removeProblemFromProblemSet(Long problemSetId, Long problemId) {
        ProblemSetEntity problemSetEntity = problemSetRepository.findOneById(problemSetId);
        if (problemSetEntity == null) {
            throw new NotFoundException("B0011");
        }
        JudgeProblemEntity problemEntity = problemRepository.findOneById(problemId);
        if (problemEntity == null) {
            throw new NotFoundException("B0002");
        }
        boolean isDeleted = problemSetEntity.getProblems().remove(problemEntity);
        if (!isDeleted) {
            // 走到这里说明这个题目集中没有这个问题
            throw new NotFoundException("B0012");
        }
        problemSetRepository.save(problemSetEntity);
    }


    /**
     * @param problemSetId 操作的题目集id
     * @author yuzhanglong
     * @date 2020-08-11 16:56:36
     * @description 从题目集中移除某个问题(不删除问题)
     * 如果这个题目集中没有这个问题，
     * 则数据库内容不会发生改变，
     * 并且我们会抛出一个全局异常 编号为 B0012
     */
    public ProblemSetEntity getProblemSetById(Long problemSetId) {
        ProblemSetEntity problemSetEntity = problemSetRepository.findOneById(problemSetId);
        if (problemRepository == null) {
            throw new NotFoundException("B0011");
        }
        return problemSetEntity;
    }

    /**
     * @param problemSetEntity 题目集实体类
     * @author yuzhanglong
     * @description ProblemSetConditionEnum 状态枚举类
     * @date 2020-08-12 10:10:24
     * @description 获取题目集的状态
     */
    public ProblemSetConditionEnum getProblemSetCondition(ProblemSetEntity problemSetEntity) {
        Date startTime = problemSetEntity.getStartTime();
        Date deadline = problemSetEntity.getDeadline();
        Integer timeCondition = DateTimeUtil.checkTimeCondition(startTime, deadline);
        if (timeCondition == DateTimeUtil.TOO_LATE) {
            return ProblemSetConditionEnum.CLOSED;
        }
        if (timeCondition == DateTimeUtil.TOO_EARLY) {
            return ProblemSetConditionEnum.NOT_STARTED;
        }
        return ProblemSetConditionEnum.RUNNING;
    }
}