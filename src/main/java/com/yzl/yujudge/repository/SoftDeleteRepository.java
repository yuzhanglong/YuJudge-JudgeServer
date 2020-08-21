package com.yzl.yujudge.repository;

import com.yzl.yujudge.model.SoftDeleteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 软删除 JpaRepository
 *
 * @author yuzhanglong
 * @date 2020-8-21 19:13:14
 */
@NoRepositoryBean
public interface SoftDeleteRepository<T extends SoftDeleteEntity> extends JpaRepository<T, Long> {


    /**
     * 执行软删除
     *
     * @param deleteDate 删除时间
     * @param id         被软删除对象的id
     * @author yuzhanglong
     * @date 2020-8-21 19:13:14
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query("UPDATE #{#entityName} t SET t.deleteTime = ?2 WHERE t.id = ?1")
    void softDeleteByIdAndDeleteTime(Long id, Date deleteDate);

    /**
     * 执行软删除(默认方法)
     *
     * @param id 被软删除对象的id
     * @author yuzhanglong
     * @date 2020-8-21 19:16:20
     */
    default void softDeleteById(Long id) {
        softDeleteByIdAndDeleteTime(id, new Date());
    }
}
