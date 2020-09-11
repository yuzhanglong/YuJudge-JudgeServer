package com.yzl.yujudge.controller.v1;


import com.yzl.yujudge.core.authorization.AuthorizationRequired;
import com.yzl.yujudge.core.common.UnifiedResponse;
import com.yzl.yujudge.core.enumeration.PermissionEnum;
import com.yzl.yujudge.dto.ProblemSetDTO;
import com.yzl.yujudge.dto.UpdateProblemSetProblemDTO;
import com.yzl.yujudge.model.JudgeProblemEntity;
import com.yzl.yujudge.model.ProblemSetEntity;
import com.yzl.yujudge.service.ProblemSetService;
import com.yzl.yujudge.utils.EntityToVoMapper;
import com.yzl.yujudge.vo.CountSubmissionByTimeVO;
import com.yzl.yujudge.vo.PaginationVO;
import com.yzl.yujudge.vo.ProblemBasicVO;
import com.yzl.yujudge.vo.ProblemSetVO;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 题目集相关控制层
 *
 * @author yuzhanglong
 * @date 2020-7-29 00:30:19
 */

@RestController
@Validated
@RequestMapping("/problem_set")
public class ProblemSetController {
    private final ProblemSetService problemSetService;

    public ProblemSetController(ProblemSetService problemSetService) {
        this.problemSetService = problemSetService;
    }


    /**
     * 获取多个题目集信息
     *
     * @param limit 限定截止时间，只选出活跃的题目集合
     * @param count 数量
     * @param start 页码
     * @author yuzhanglong
     * @date 2020-08-08 22:38:45
     */
    @GetMapping("/get_problem_sets")
    @AuthorizationRequired
    public UnifiedResponse getProblemSets(
            @RequestParam(defaultValue = "0") Integer start,
            @RequestParam(defaultValue = "10") Integer count,
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "false") Boolean limit) {
        Page<ProblemSetEntity> items = problemSetService.getProblemSetInfo(start, count, search, limit);
        PaginationVO<ProblemSetEntity, ProblemSetVO> paginationVO = new PaginationVO<>(items, ProblemSetVO.class);
        return new UnifiedResponse(paginationVO);
    }

    /**
     * 通过题目集id来获取其对应的problems
     *
     * @param problemSetId 题目集id
     * @author yuzhanglong
     * @date 2020-08-10 18:50:07
     */
    @GetMapping("/get_problem_set_problems")
    @AuthorizationRequired
    public UnifiedResponse getProblemSetById(
            @RequestParam(defaultValue = "") Long problemSetId,
            @RequestParam(defaultValue = "0") Integer start,
            @RequestParam(defaultValue = "10") Integer count) {
        Page<JudgeProblemEntity> items = problemSetService.getProblemSetProblems(start, count, problemSetId);
        PaginationVO<JudgeProblemEntity, ProblemBasicVO> paginationVO = new PaginationVO<>(items, ProblemBasicVO.class);
        return new UnifiedResponse(paginationVO);
    }

    /**
     * 创建一个题目集
     *
     * @param problemSetDTO 题目集的数据传输对象
     * @author yuzhanglong
     * @date 2020-08-09 15:32:25
     */
    @PostMapping("/create_problem_set")
    @AuthorizationRequired(permission = PermissionEnum.PROBLEM_MANAGER)
    public UnifiedResponse createProblemSet(@RequestBody @Validated ProblemSetDTO problemSetDTO) {
        problemSetService.createProblemSet(problemSetDTO);
        return new UnifiedResponse("创建题目集成功");
    }


    /**
     * 为题目集添加一个或多个题目
     *
     * @param problemSetProblemDTO 为题目集添加题目的数据传输对象
     * @author yuzhanglong
     * @date 2020-08-10 18:58:08
     */
    @PutMapping("/update_problem_set_problem")
    @AuthorizationRequired(permission = PermissionEnum.PROBLEM_MANAGER)
    public UnifiedResponse updateProblemSetProblem(
            @RequestBody @Validated UpdateProblemSetProblemDTO problemSetProblemDTO) {
        List<Long> problemId = problemSetProblemDTO.getProblems();
        Long problemSetId = problemSetProblemDTO.getProblemSetId();
        problemSetService.updateProblemSetProblem(problemSetId, problemId);
        return new UnifiedResponse("添加成功");
    }

    /**
     * 从题目集中移除某个问题(不删除问题)
     *
     * @param problemId    要从题目集中移除的问题id
     * @param problemSetId 操作的题目集id
     * @author yuzhanglong
     * @date 2020-08-10 23:05:18
     */
    @DeleteMapping("/remove_from_problem_set")
    @AuthorizationRequired(permission = PermissionEnum.PROBLEM_MANAGER)
    public UnifiedResponse removeFromProblemSet(
            @RequestParam Long problemSetId,
            @RequestParam Long problemId) {
        problemSetService.removeProblemFromProblemSet(problemSetId, problemId);
        return new UnifiedResponse("移除成功");
    }

    /**
     * 获取题目集信息
     *
     * @param problemSetId 获取题目集信息
     * @author yuzhanglong
     * @date 2020-08-12 00:43:38
     */
    @GetMapping("/get_problem_set/{problemSetId}")
    @AuthorizationRequired
    public UnifiedResponse getProblemSetById(@PathVariable Long problemSetId) {
        ProblemSetEntity problemSet = problemSetService.validateAndGetProblemSetById(problemSetId);
        EntityToVoMapper<ProblemSetEntity, ProblemSetVO> mapper = new EntityToVoMapper<>(problemSet, ProblemSetVO.class);
        ProblemSetVO problemSetVO = mapper.getViewObject();
        problemSetVO.setCondition(problemSetService.getProblemSetCondition(problemSet));
        return new UnifiedResponse(mapper.getViewObject());
    }


    /**
     * 获取记分板信息
     *
     * @param problemSetId 获取记分板信息
     * @author yuzhanglong
     * @date 2020-08-11 00:11:32
     */
    @GetMapping("/get_score_board/{problemSetId}")
    @AuthorizationRequired
    public UnifiedResponse getProblemSetScoreBoard(@PathVariable Long problemSetId) {
        return new UnifiedResponse(problemSetService.getProblemSetScoreBoardCache(problemSetId));
    }

    /**
     * 更新某个题目集的基本信息
     *
     * @param problemSetId 题目集id
     * @author yuzhanglong
     * @date 2020-08-16 00:02:14
     */
    @PutMapping("/update_problem_set_basic_info/{problemSetId}")
    @AuthorizationRequired
    public UnifiedResponse updateProblemSetBasicInfo(
            @PathVariable Long problemSetId,
            @RequestBody @Validated ProblemSetDTO problemSetDTO) {
        problemSetService.updateProblemSetBasicInfo(problemSetId, problemSetDTO);
        return new UnifiedResponse("编辑题目集基本信息成功");
    }

    /**
     * 统计某个题目集活跃区间内的统计数据
     *
     * @param problemSetId 题目集id
     * @author yuzhanglong
     * @date 2020-8-20 10:49:30
     */
    @GetMapping("/count_problem_set_submission")
    @AuthorizationRequired
    public UnifiedResponse countProblemSetSubmission(@RequestParam Long problemSetId) {
        CountSubmissionByTimeVO res = problemSetService.countProblemSetSubmission(problemSetId);
        return new UnifiedResponse(res);
    }

    /**
     * 获取题目集散点结果列表
     *
     * @param problemSetId 题目集id
     * @return 题目集散点结果列表
     * @author yuzhanglong
     * @date 2020-8-20 14:38:42
     * @deprecated 暂不使用
     */
    @GetMapping("/get_scatter")
    @Deprecated
    public UnifiedResponse countProblemSetConditionScatter(@RequestParam Long problemSetId) {
        List<Map<String, Object>> res = problemSetService.countSubmissionConditionScatter(problemSetId);
        return new UnifiedResponse(res);
    }

    /**
     * 获取题目集时间线
     *
     * @param problemSetId 题目集id
     * @param start        页码
     * @param count        每页个数
     * @author yuzhanglong
     * @date 2020-9-4 23:32:56
     */
    @GetMapping("/timeline")
    @AuthorizationRequired
    public UnifiedResponse getProblemSetTimeLine(
            @RequestParam Long problemSetId,
            @RequestParam(defaultValue = "0") Integer start,
            @RequestParam(defaultValue = "10") Integer count) {
        return new UnifiedResponse(problemSetService.getProblemSetTimeLine(problemSetId, start, count));
    }

    /**
     * 移除一个题目集
     *
     * @param problemSetId 题目集id
     * @author yuzhanglong
     * @date 2020-9-6 14:39:41
     */
    @DeleteMapping("/problem_set/{problemSetId}")
    @AuthorizationRequired
    public UnifiedResponse deleteProblemSet(@PathVariable Long problemSetId) {
        problemSetService.deleteProblemSet(problemSetId);
        return new UnifiedResponse("删除成功~");
    }
}