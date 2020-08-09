package com.yzl.yujudge.controller.v1;


import com.yzl.yujudge.core.common.UnifiedResponse;
import com.yzl.yujudge.model.JudgeProblemEntity;
import com.yzl.yujudge.model.ProblemSetEntity;
import com.yzl.yujudge.service.ProblemSetService;
import com.yzl.yujudge.utils.EntityAndVoListMapper;
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
            @RequestParam(defaultValue = "false") Boolean limit) {
        Page<ProblemSetEntity> items = problemSetService.getProblemSetInfo(start, count, limit);
        PaginationVO<ProblemSetEntity, ProblemSetVO> paginationVO = new PaginationVO<>(items, ProblemSetVO.class);
        return new UnifiedResponse(paginationVO);
    }

    /**
     * @param problemSetId 题目集id
     * @author yuzhanglong
     * @date 2020-08-09 12:55:19
     * @description 通过题目集id来获取其对应的problems
     */
    @GetMapping("/get_problem_set_problems/{problemSetId}")
    public UnifiedResponse getProblemSetById(@PathVariable Long problemSetId) {
        List<JudgeProblemEntity> problemSetEntity = problemSetService.getProblemSetProblems(problemSetId);
        EntityAndVoListMapper<JudgeProblemEntity, ProblemBasicVO> mapper = new EntityAndVoListMapper<>(problemSetEntity, ProblemBasicVO.class);
        return new UnifiedResponse(mapper.getItems());
    }
}