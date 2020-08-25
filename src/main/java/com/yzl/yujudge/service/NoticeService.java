package com.yzl.yujudge.service;

import com.yzl.yujudge.model.NoticeEntity;
import com.yzl.yujudge.repository.NoticeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * 公告相关业务逻辑
 *
 * @author yuzhanglong
 * @date 2020-8-25 23:50:36
 */

@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }


    /**
     * 分页获取所有通知
     *
     * @param start 页码
     * @param count 每一页的个数
     * @author yuzhanglong
     * @date 2020-8-26 00:14:56
     */
    public Page<NoticeEntity> getNotices(Integer start, Integer count) {
        Pageable pageRequest = PageRequest.of(start, count);
        return noticeRepository.findNoticesOrderByPriorityAndTime(pageRequest);
    }
}
