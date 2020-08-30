package com.yzl.yujudge.controller.v1;

import com.yzl.yujudge.core.authorization.AuthorizationRequired;
import com.yzl.yujudge.core.authorization.UserHolder;
import com.yzl.yujudge.core.common.UnifiedResponse;
import com.yzl.yujudge.core.configuration.SubmissionExecutorConfiguration;
import com.yzl.yujudge.dto.SubmissionDTO;
import com.yzl.yujudge.model.SubmissionEntity;
import com.yzl.yujudge.service.SubmissionService;
import com.yzl.yujudge.utils.DateTimeUtil;
import com.yzl.yujudge.utils.EntityToVoMapper;
import com.yzl.yujudge.vo.PaginationVO;
import com.yzl.yujudge.vo.SubmissionDetailVO;
import com.yzl.yujudge.vo.SubmissionVO;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 提交相关控制层
 *
 * @author yuzhanglong
 * @date 2020-7-29 00:29:59
 */

@RestController
@Validated
@RequestMapping("/submission")
public class SubmissionController {
    private final SubmissionService submissionService;
    private final SubmissionExecutorConfiguration submissionExecutorConfiguration;

    public SubmissionController(SubmissionService submissionService, SubmissionExecutorConfiguration executorConfiguration) {
        this.submissionService = submissionService;
        this.submissionExecutorConfiguration = executorConfiguration;
    }


    /**
     * 提交代码
     *
     * @author yuzhanglong
     * @date 2020-7-29 12:57:00
     */
    @PostMapping("/submit_code")
    @AuthorizationRequired
    public UnifiedResponse submitCode(@Validated @RequestBody SubmissionDTO submissionDTO) {
        // 获取submission实体对象，当我们拿到它之后，说明这个submission已经被保存了
        SubmissionEntity submissionEntity = submissionService.initSubmission(submissionDTO);
        submissionService.addSubmissionTask(submissionEntity, 0);
        return new UnifiedResponse("提交已经开始处理");
    }

    /**
     * 查看submission调度的线程池状态
     *
     * @author yuzhanglong
     * @date 2020-8-2 18:00
     */
    @GetMapping("/get_submit_condition")
    @AuthorizationRequired
    public UnifiedResponse getSubmitCondition() {
        // TODO: 获取submission线程池状态
        ThreadPoolExecutor threadPoolExecutor = submissionExecutorConfiguration.submissionAsyncServiceExecutor();
        return new UnifiedResponse(threadPoolExecutor.getActiveCount());
    }


    /**
     * 获取某个problem下的用户提交(分页)
     *
     * @param start     开始的条目
     * @param problemId 目标问题id
     * @author yuzhanglong
     * @date 2020-7-31 20:06:36
     */
    @GetMapping("/get_submissions")
    @AuthorizationRequired
    public UnifiedResponse getSubmissions(
            @RequestParam(defaultValue = "0") Integer start,
            @RequestParam(defaultValue = "10") Integer count,
            @RequestParam @NotNull Long problemId) {
        Page<SubmissionEntity> submissionEntities = submissionService.getSubmissionByProblemId(start, count, problemId);
        PaginationVO<SubmissionEntity, SubmissionVO> data = new PaginationVO<>(submissionEntities, SubmissionVO.class);
        return new UnifiedResponse(data);
    }

    /**
     * 获取某个submission的详细信息
     *
     * @param submissionId 某次提交的id
     * @author yuzhanglong
     * @date 2020-8-1 11:42:46
     */
    @GetMapping("/get_submission_detail")
    @AuthorizationRequired
    public UnifiedResponse getSubmissionDetail(@RequestParam @NotNull Long submissionId) {
        // TODO: 提交详情某些内容（例如代码）是否应该开放？我们需要一个权限控制
        SubmissionEntity submission = submissionService.getSubmissionDataById(submissionId);
        EntityToVoMapper<SubmissionEntity, SubmissionDetailVO> mapper = new EntityToVoMapper<>(submission, SubmissionDetailVO.class);
        return new UnifiedResponse(mapper.getViewObject());
    }

    /**
     * 获取某用户某个时间段内的提交, 按天为单位分割
     *
     * @param begin 开始时间
     * @param end   结束时间
     * @author yuzhanglong
     * @date 2020-8-20 19:17:26
     */
    @GetMapping("/get_user_recent_submission")
    @AuthorizationRequired
    public UnifiedResponse getUserRecentSubmission(
            @RequestParam String begin,
            @RequestParam String end) {
        Long userId = UserHolder.getUserId();
        List<Map<String, Object>> res = submissionService.countUserRecentSubmission(
                userId,
                DateTimeUtil.formatDateTimeString(begin),
                DateTimeUtil.formatDateTimeString(end));
        return new UnifiedResponse(res);
    }

    /**
     * 获取用户判题结果的相关信息，例如wa数目、ac数目、tle数目等
     *
     * @author yuzhanglong
     * @date 2020-8-21 00:43:32
     */
    @GetMapping("/get_user_judge_result_count")
    @AuthorizationRequired
    public UnifiedResponse getUserJudgeResultCount() {
        Long userId = UserHolder.getUserId();
        List<Map<String, Object>> res = submissionService.countUserJudgeResult(userId);
        return new UnifiedResponse(res);
    }
}