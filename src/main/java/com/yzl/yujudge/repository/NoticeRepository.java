package com.yzl.yujudge.repository;

import com.yzl.yujudge.model.NoticeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

/**
 * 公告相关查询接口
 *
 * @author yuzhanglong
 * @date 2020-8-26 00:03:36
 */
public interface NoticeRepository extends SoftDeleteRepository<NoticeEntity> {
    /**
     * 获取公告
     * 我们根据以下特征进行排序：
     * 1. priority 即优先级，优先级高的永远在最前面
     * 2. createTime 即创建时间，时间晚（新）的靠前
     *
     * @param pageable 分页对象
     * @return NoticeEntity 的分页对象
     * @author yuzhanglong
     * @date 2020-8-26 00:10:27
     * @description 分页获取公告
     */

    @Query("select notice from NoticeEntity notice " +
            "order by notice.priority, notice.createTime desc ")
    Page<NoticeEntity> findNoticesOrderByPriorityAndTime(Pageable pageable);
}
