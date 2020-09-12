package com.yzl.yujudge.controller.v1;

import com.yzl.yujudge.core.common.UnifiedResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 一级url
 *
 * @author yuzhanglong
 * @date 2020-9-12 22:40:22
 */

@RestController
@Validated
public class BaseController {

    /**
     * 连接测试
     *
     * @author yuzhanglong
     * @date 2020-9-12 22:42:32
     */
    @GetMapping("/")
    public UnifiedResponse checkConnection() {
        return new UnifiedResponse("Your project is running successfully! O(∩_∩)O");
    }
}
