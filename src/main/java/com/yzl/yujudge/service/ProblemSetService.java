package com.yzl.yujudge.service;


import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.yzl.yujudge.core.authorization.UserHolder;
import com.yzl.yujudge.core.exception.http.NotFoundException;
import com.yzl.yujudge.dto.ProblemSetDTO;
import com.yzl.yujudge.model.JudgeProblemEntity;
import com.yzl.yujudge.model.ProblemSetEntity;
import com.yzl.yujudge.model.UserEntity;
import com.yzl.yujudge.repository.ProblemSetRepository;
import com.yzl.yujudge.repository.UserRepository;
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


    public ProblemSetService(ProblemSetRepository problemSetRepository, UserRepository userRepository) {
        this.problemSetRepository = problemSetRepository;
        this.userRepository = userRepository;
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
}