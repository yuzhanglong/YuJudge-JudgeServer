package com.yzl.yujudge.controller.v1;


import com.yzl.yujudge.core.authorization.AuthorizationRequired;
import com.yzl.yujudge.core.common.UnifiedResponse;
import com.yzl.yujudge.dto.ProblemSetDTO;
import com.yzl.yujudge.dto.UpdateProblemSetProblemDTO;
import com.yzl.yujudge.model.JudgeProblemEntity;
import com.yzl.yujudge.model.ProblemSetEntity;
import com.yzl.yujudge.service.ProblemSetService;
import com.yzl.yujudge.utils.EntityToVoMapper;
import com.yzl.yujudge.vo.PaginationVO;
import com.yzl.yujudge.vo.ProblemBasicVO;
import com.yzl.yujudge.vo.ProblemSetVO;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author yuzhanglong
 * @date 2020-7-29 00:30:19
 * @description 题目集相关控制层
 */

@RestController
@Validated
@CrossOrigin
@RequestMapping("/problem_set")
public class ProblemSetController {
    private final ProblemSetService problemSetService;

    public ProblemSetController(ProblemSetService problemSetService) {
        this.problemSetService = problemSetService;
    }


    /**
     * @param limit 限定截止时间，只选出活跃的题目集合
     * @param count 数量
     * @param start 页码
     * @author yuzhanglong
     * @date 2020-08-08 22:38:45
     * @description 获取题目集信息
     */
    @GetMapping("/get_problem_sets")
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
     * @param problemSetId 题目集id
     * @author yuzhanglong
     * @date 2020-08-10 18:50:07
     * @description 通过题目集id来获取其对应的problems
     */
    @GetMapping("/get_problem_set_problems/{problemSetId}")
    public UnifiedResponse getProblemSetById(
            @PathVariable Long problemSetId,
            @RequestParam(defaultValue = "0") Integer start,
            @RequestParam(defaultValue = "10") Integer count) {
        Page<JudgeProblemEntity> items = problemSetService.getProblemSetProblems(start, count, problemSetId);
        PaginationVO<JudgeProblemEntity, ProblemBasicVO> paginationVO = new PaginationVO<>(items, ProblemBasicVO.class);
        return new UnifiedResponse(paginationVO);
    }

    /**
     * @param problemSetDTO 题目集的数据传输对象
     * @author yuzhanglong
     * @date 2020-08-09 15:32:25
     * @description 创建一个题目集
     */
    @PostMapping("/create_problem_set")
    @AuthorizationRequired
    public UnifiedResponse createProblemSet(@RequestBody @Validated ProblemSetDTO problemSetDTO) {
        problemSetService.createProblemSet(problemSetDTO);
        return new UnifiedResponse("创建题目集成功");
    }


    /**
     * @param problemSetProblemDTO 为题目集添加题目的数据传输对象
     * @author yuzhanglong
     * @date 2020-08-10 18:58:08
     * @description 为题目集添加一个或多个题目
     */
    @PutMapping("/update_problem_set_problem")
    public UnifiedResponse updateProblemSetProblem(
            @RequestBody @Validated UpdateProblemSetProblemDTO problemSetProblemDTO) {
        List<Long> problemId = problemSetProblemDTO.getProblems();
        Long problemSetId = problemSetProblemDTO.getProblemSetId();
        problemSetService.updateProblemSetProblem(problemSetId, problemId);
        return new UnifiedResponse("添加成功");
    }

    /**
     * @param problemId    要从题目集中移除的问题id
     * @param problemSetId 操作的题目集id
     * @author yuzhanglong
     * @date 2020-08-10 23:05:18
     * @description 从题目集中移除某个问题(不删除问题)
     */
    @DeleteMapping("/remove_from_problem_set")
    public UnifiedResponse removeFromProblemSet(
            @RequestParam Long problemSetId,
            @RequestParam Long problemId) {
        problemSetService.removeProblemFromProblemSet(problemSetId, problemId);
        return new UnifiedResponse("移除成功");
    }

    /**
     * @param problemSetId 获取题目集信息
     * @author yuzhanglong
     * @date 2020-08-12 00:43:38
     * @description 获取题目集信息
     */
    @GetMapping("/get_problem_set/{problemSetId}")
    public UnifiedResponse getProblemSetById(@PathVariable Long problemSetId) {
        ProblemSetEntity problemSet = problemSetService.getProblemSetById(problemSetId);
        EntityToVoMapper<ProblemSetEntity, ProblemSetVO> mapper = new EntityToVoMapper<>(problemSet, ProblemSetVO.class);
        ProblemSetVO problemSetVO = mapper.getViewObject();
        problemSetVO.setCondition(problemSetService.getProblemSetCondition(problemSet));
        return new UnifiedResponse(mapper.getViewObject());
    }

    @GetMapping("/get_score_board/{problemSetId}")
    @AuthorizationRequired
    public UnifiedResponse getProblemSetScoreBoard(@PathVariable Long problemSetId) {
        return new UnifiedResponse(problemSetService.getProblemSetScoreBoardCache(problemSetId));
    }
}