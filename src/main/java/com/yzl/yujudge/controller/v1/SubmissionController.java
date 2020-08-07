package com.yzl.yujudge.controller.v1;

import com.yzl.yujudge.core.authorization.AuthorizationRequired;
import com.yzl.yujudge.core.common.UnifiedResponse;
import com.yzl.yujudge.core.configuration.SubmissionExecutorConfiguration;
import com.yzl.yujudge.dto.SubmissionDTO;
import com.yzl.yujudge.model.SubmissionEntity;
import com.yzl.yujudge.service.SubmissionService;
import com.yzl.yujudge.utils.ToVoUtil;
import com.yzl.yujudge.vo.PaginationVO;
import com.yzl.yujudge.vo.SubmissionDetailVO;
import com.yzl.yujudge.vo.SubmissionVO;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
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
    @AuthorizationRequired
    public UnifiedResponse submitCode(@Validated @RequestBody SubmissionDTO submissionDTO) {
        // 获取submission实体对象，当我们拿到它之后，说明这个submission已经被保存了
        SubmissionEntity submissionEntity = submissionService.initSubmission(submissionDTO);
        submissionService.addSubmissionTask(submissionEntity);
        return new UnifiedResponse("提交已经开始处理");
    }

    /**
     * @author yuzhanglong
     * @date 2020-8-2 18:00
     * @description 查看submission调度的线程池状态
     */
    @GetMapping("/get_submit_condition")
    public UnifiedResponse getSubmitCondition() {
        // TODO: 获取submission线程池状态
        ThreadPoolExecutor threadPoolExecutor = submissionExecutorConfiguration.submissionAsyncServiceExecutor();
        return new UnifiedResponse(threadPoolExecutor.getActiveCount());
    }


    /**
     * @param start     开始的条目
     * @param problemId 目标问题id
     * @author yuzhanglong
     * @description 获取某个problem下的用户提交(分页)
     * @date 2020-7-31 20:06:36
     */
    @GetMapping("/get_submissions")
    public UnifiedResponse getSubmissions(
            @RequestParam(defaultValue = "0") Integer start,
            @RequestParam(defaultValue = "10") Integer count,
            @RequestParam @NotNull Long problemId) {
        Page<SubmissionEntity> submissionEntities = submissionService.getSubmissionByProblemId(start, count, problemId);
        PaginationVO<SubmissionEntity, SubmissionVO> data = new PaginationVO<>(submissionEntities, SubmissionVO.class);
        return new UnifiedResponse(data);
    }

    /**
     * @param submissionId 某次提交的id
     * @author yuzhanglong
     * @description 获取某个submission的详细信息
     * @date 2020-8-1 11:42:46
     */
    @GetMapping("/get_submission_detail")
    public UnifiedResponse getSubmissionDetail(@RequestParam @NotNull Long submissionId) {
        // TODO: 提交详情某些内容（例如代码）是否应该开放？我们需要一个权限控制
        SubmissionEntity submission = submissionService.getSubmissionDataById(submissionId);
        SubmissionDetailVO submissionDetailVO = ToVoUtil.submissionEntityToSubmissionDetailVO(submission);
        return new UnifiedResponse(submissionDetailVO);
    }
}