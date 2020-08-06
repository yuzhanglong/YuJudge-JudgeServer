package com.yzl.yujudge.api.v1;

import com.yzl.yujudge.core.common.UnifiedResponse;
import com.yzl.yujudge.service.CommonService;
import com.yzl.yujudge.vo.UploadTokenVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuzhanglong
 * @description 一般性接口的控制层
 * @date 2020-08-05 20:42:59
 */

@RestController
@Validated
@CrossOrigin
@RequestMapping("/common")
public class CommonController {
    private final CommonService commonService;

    public CommonController(CommonService commonService) {
        this.commonService = commonService;
    }

    /**
     * @author yuzhanglong
     * @description 获取上传凭证
     * @date 2020-08-05 21:02:07
     */
    @GetMapping("/upload_token")
    public UnifiedResponse getUploadToken() {
        String token = commonService.getUploadToken();
        UploadTokenVO uploadTokenVO = new UploadTokenVO();
        uploadTokenVO.setUploadToken(token);
        return new UnifiedResponse(uploadTokenVO);
    }
}
