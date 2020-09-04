package com.yzl.yujudge.repository;

import com.yzl.yujudge.model.JudgeProblemEntity;
import com.yzl.yujudge.model.ProblemSetEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * problem相关的查询类接口
 *
 * @author yuzhanglong
 * @date 2020-7-18 04:47:16
 */
public interface ProblemRepository extends JpaRepository<JudgeProblemEntity, Long> {
    /**
     * 通过题目Id从数据库中查询题目信息
     *
     * @param id 需要查询问题的id
     * @return JudgeProblemEntity 题目信息实体类
     * @author yuzhanglong
     * @date 2020-7-18 04:47:16
     */
    JudgeProblemEntity findOneById(Long id);


    /**
     * 对问题基于限制数量的查询，以创建时间排序
     *
     * @param pageable 分页参数
     * @return List JudgeProblemEntity 多个题目信息实体类
     * @author yuzhanglong
     * @date 2020-08-06 21:01:06
     */
    List<JudgeProblemEntity> findByOrderByCreateTimeDesc(Pageable pageable);


    /**
     * 分页获取某题目集下的所有题目
     *
     * @param problemSetEntity problemSet的实体对象
     * @param pageable         分页对象
     * @return Page<JudgeProblemEntity> 某题目集下的所有题目的分页对象
     * @author yuzhanglong
     * @date 2020-08-10 10:21:47
     */
    Page<JudgeProblemEntity> findAllByProblemSetEntityList(ProblemSetEntity problemSetEntity, Pageable pageable);


    /**
     * 分页获取题目
     *
     * @param pageable 分页配置
     * @param name     关键字
     * @return List JudgeProblemEntity 的分页对象
     * @author yuzhanglong
     * @date 2020-08-10 20:29:17
     */
    @Query("select p from JudgeProblemEntity p " +
            "where p.name like %?1% " +
            "order by p.id asc")
    Page<JudgeProblemEntity> findByName(String name, Pageable pageable);

    /**
     * 根据名称获取problem
     *
     * @param name 名称
     * @return List JudgeProblemEntity 的分页对象
     * @author yuzhanglong
     * @date 2020-9-4 21:50:34
     */
    JudgeProblemEntity findTop1ByName(String name);
}