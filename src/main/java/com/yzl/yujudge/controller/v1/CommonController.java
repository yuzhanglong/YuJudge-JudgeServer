package com.yzl.yujudge.controller.v1;

import com.yzl.yujudge.core.authorization.AuthorizationRequired;
import com.yzl.yujudge.core.common.UnifiedResponse;
import com.yzl.yujudge.core.enumeration.PermissionEnum;
import com.yzl.yujudge.service.CommonService;
import com.yzl.yujudge.vo.DailyWordVO;
import com.yzl.yujudge.vo.GlobalCountVO;
import com.yzl.yujudge.vo.UploadTokenVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 一般性接口的控制层
 *
 * @author yuzhanglong
 * @date 2020-08-05 20:42:59
 */

@RestController
@Validated
@RequestMapping("/common")
public class CommonController {
    private final CommonService commonService;

    public CommonController(CommonService commonService) {
        this.commonService = commonService;
    }

    /**
     * 获取上传凭证
     *
     * @author yuzhanglong
     * @date 2020-08-05 21:02:07
     */
    @GetMapping("/upload_token")
    @AuthorizationRequired
    public UnifiedResponse getUploadToken() {
        String token = commonService.getUploadToken();
        UploadTokenVO uploadTokenVO = new UploadTokenVO();
        uploadTokenVO.setUploadToken(token);
        return new UnifiedResponse(uploadTokenVO);
    }


    /**
     * 获取全局统计数据，包括问题总数目、提交总数目等内容
     *
     * @author yuzhanglong
     * @date 2020-8-24 18:26:13
     */
    @GetMapping("/get_global_count")
    @AuthorizationRequired
    public UnifiedResponse getGlobalCount() {
        GlobalCountVO count = commonService.getGlobalCount();
        return new UnifiedResponse(count);
    }

    /**
     * 获取每日一句
     *
     * @author yuzhanglong
     * @date 2020-8-30 21:18:31
     */
    @GetMapping("/get_daily_word")
    @AuthorizationRequired
    public UnifiedResponse getDailyWord() {
        DailyWordVO dailyWordVO = commonService.getDailyWord();
        return new UnifiedResponse(dailyWordVO);
    }

    /**
     * 开启或者关闭验证码验证功能
     *
     * @author yuzhanglong
     * @date 2020-8-30 21:18:31
     */
    @PutMapping("/check_code_condition")
    @AuthorizationRequired(permission = PermissionEnum.ADMIN)
    public UnifiedResponse resetCheckCodeCondition() {
        Boolean res = commonService.resetCheckCodeCondition();
        return new UnifiedResponse("验证码验证功能已重置，当前状态" + (res ? "已开启" : "已关闭"));
    }

    /**
     * 获取验证码验证状态
     *
     * @author yuzhanglong
     * @date 2020-9-13 11:20:39
     */
    @GetMapping("/check_code_condition")
    @AuthorizationRequired(permission = PermissionEnum.ADMIN)
    public UnifiedResponse getCheckCodeCondition() {
        Boolean res = commonService.getCheckCodeCondition();
        return new UnifiedResponse(res);
    }
}