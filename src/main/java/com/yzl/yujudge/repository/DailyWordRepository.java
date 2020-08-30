package com.yzl.yujudge.repository;


import com.yzl.yujudge.model.DailyWordEntity;

/**
 * 每日一句查询对象
 *
 * @author yuzhanglong
 * @date 2020-8-30 21:00:00
 */
public interface DailyWordRepository extends BaseRepository<DailyWordEntity> {
    /**
     * 通过id寻找每日一句
     *
     * @param id id
     * @return DailyWordEntity 每日一句实体类
     * @author yuzhanglong
     * @date 2020-8-30 21:12:56
     */
    DailyWordEntity findOneById(Long id);
}
