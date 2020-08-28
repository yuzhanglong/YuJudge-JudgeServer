package com.yzl.yujudge.controller.v1;

import com.yzl.yujudge.core.common.UnifiedResponse;
import com.yzl.yujudge.model.NoticeEntity;
import com.yzl.yujudge.service.NoticeService;
import com.yzl.yujudge.vo.NoticeBasicVO;
import com.yzl.yujudge.vo.PaginationVO;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 公告相关控制层
 *
 * @author yuzhanglong
 * @date 2020-8-25 23:44:53
 */
@RestController
@Validated
@CrossOrigin
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    /**
     * 分页获取所有通知
     *
     * @param start 页码
     * @param count 每一页的个数
     * @author yuzhanglong
     * @date 2020-8-25 23:49:00
     */
    @GetMapping("/get_notices")
    public UnifiedResponse getNotices(
            @RequestParam(defaultValue = "0") Integer start,
            @RequestParam(defaultValue = "10") Integer count) {
        Page<NoticeEntity> notices = noticeService.getNotices(start, count);
        PaginationVO<NoticeEntity, NoticeBasicVO> paginationVO = new PaginationVO<>(notices, NoticeBasicVO.class);
        return new UnifiedResponse(paginationVO);
    }


    @DeleteMapping("/delete_notice")
    public UnifiedResponse deleteNotice() {
        // TODO: 删除
        return new UnifiedResponse("删除成功");
    }


    @PutMapping("/update_notice")
    public UnifiedResponse updateNotice() {
        // TODO: 更新
        return new UnifiedResponse("更新成功");
    }
}
