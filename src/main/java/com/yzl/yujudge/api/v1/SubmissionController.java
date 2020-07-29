package com.yzl.yujudge.api.v1;

import com.yzl.yujudge.core.common.UnifiedResponse;
import com.yzl.yujudge.dto.SubmissionDTO;
import com.yzl.yujudge.service.SubmissionService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author yuzhanglong
 * @date 2020-7-29 00:29:59
 * @description 提交相关控制层
 */

@RestController
@Validated
@CrossOrigin
@RequestMapping("/submission")
public class SubmissionController {
    private final SubmissionService submissionService;

    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }


    /**
     * @author yuzhanglong
     * @date 2020-7-29 12:57:00
     * @description 提交代码
     */
    @PostMapping("/submit_code")
    public UnifiedResponse submitCode(@Validated @RequestBody SubmissionDTO submissionDTO) {
        submissionService.submitCode(submissionDTO);
        return new UnifiedResponse("提交已经开始处理");
    }
}
