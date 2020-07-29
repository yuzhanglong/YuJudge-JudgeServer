package com.yzl.yujudge.api.v1;

import com.yzl.yujudge.core.common.UnifiedResponse;
import com.yzl.yujudge.core.configuration.SubmissionExecutorConfiguration;
import com.yzl.yujudge.dto.SubmissionDTO;
import com.yzl.yujudge.model.SubmissionEntity;
import com.yzl.yujudge.service.SubmissionService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ThreadPoolExecutor;

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
    private final SubmissionExecutorConfiguration submissionExecutorConfiguration;

    public SubmissionController(SubmissionService submissionService, SubmissionExecutorConfiguration executorConfiguration) {
        this.submissionService = submissionService;
        this.submissionExecutorConfiguration = executorConfiguration;
    }


    /**
     * @author yuzhanglong
     * @date 2020-7-29 12:57:00
     * @description 提交代码
     */
    @PostMapping("/submit_code")
    public UnifiedResponse submitCode(@Validated @RequestBody SubmissionDTO submissionDTO) {
        // 获取submission实体对象，当我们拿到它之后，说明这个submission已经被保存了
        SubmissionEntity submissionEntity = submissionService.initSubmission(submissionDTO);
        submissionService.addSubmissionTask(submissionEntity);
        return new UnifiedResponse("提交已经开始处理");
    }

    @GetMapping("/get_submit_condition")
    public UnifiedResponse getSubmitCondition() {
        ThreadPoolExecutor threadPoolExecutor = submissionExecutorConfiguration.submissionAsyncServiceExecutor();
        return new UnifiedResponse("获取condition成功~");
    }
}