package com.yzl.yujudge.controller.v1;

import com.github.dozermapper.core.Mapper;
import com.yzl.yujudge.core.authorization.AuthorizationRequired;
import com.yzl.yujudge.core.authorization.UserHolder;
import com.yzl.yujudge.core.common.UnifiedResponse;
import com.yzl.yujudge.core.enumeration.PermissionEnum;
import com.yzl.yujudge.dto.ProblemDTO;
import com.yzl.yujudge.dto.ProblemLimitationDTO;
import com.yzl.yujudge.dto.SolutionDTO;
import com.yzl.yujudge.model.JudgeProblemEntity;
import com.yzl.yujudge.model.JudgeSolutionEntity;
import com.yzl.yujudge.service.ProblemService;
import com.yzl.yujudge.utils.EntityToVoListMapper;
import com.yzl.yujudge.vo.PaginationVO;
import com.yzl.yujudge.vo.ProblemBasicVO;
import com.yzl.yujudge.vo.ProblemVO;
import com.yzl.yujudge.vo.SolutionVO;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * 题目相关控制层
 *
 * @author yuzhanglong
 * @date 2020-7-18 04:26
 */
@RestController
@Validated
@RequestMapping("/problem")
public class ProblemController {
    private final ProblemService problemService;
    private final Mapper mapper;

    public ProblemController(
            ProblemService problemService,
            Mapper mapper) {
        this.problemService = problemService;
        this.mapper = mapper;
    }

    /**
     * 获取题目信息的结构，调用者传入一个problem id，
     * 我们通过这个id找到problem信息并返回给调用者
     *
     * @param problemId 问题id
     * @author yuzhanglong
     * @date 2020-7-18 04:26
     */
    @GetMapping("/get_problem_by_id/{problemId}")
    @AuthorizationRequired
    public UnifiedResponse getProblemInfoById(@PathVariable Long problemId) {
        JudgeProblemEntity problem = problemService.getProblemInfoById(problemId);
        ProblemBasicVO problemBasicInfo = new ProblemBasicVO();
        BeanUtils.copyProperties(problem, problemBasicInfo);
        return new UnifiedResponse(problemBasicInfo);
    }


    /**
     * 获取某个problem的详细信息
     * 我们通过这个id找到problem详细信息并返回给调用者
     *
     * @param problemId 问题id
     * @author yuzhanglong
     * @date 2020-7-18 04:26
     */
    @GetMapping("/get_problem_detailed_by_id/{problemId}")
    @AuthorizationRequired
    public UnifiedResponse getProblemDetailedInfoById(@PathVariable Long problemId) {
        JudgeProblemEntity problem = problemService.getProblemInfoById(problemId);
        ProblemVO problemDetailed = mapper.map(problem, ProblemVO.class);
        return new UnifiedResponse(problemDetailed);
    }


    /**
     * 以分页的方式获取problem内容
     *
     * @param start  从第几条记录开始获取内容
     * @param count  获取数据的数量
     * @param search 搜索关键字
     * @author yuzhanglong
     * @date 2020-7-20 10:48:34
     */
    @GetMapping("/get_problems")
    @AuthorizationRequired
    public UnifiedResponse getProblems(
            @RequestParam(defaultValue = "0") Integer start,
            @RequestParam(defaultValue = "10") Integer count,
            @RequestParam(defaultValue = "") String search) {
        Page<JudgeProblemEntity> problems = problemService.getProblems(start, count, search);
        PaginationVO<JudgeProblemEntity, ProblemBasicVO> paginationVO = new PaginationVO<>(problems, ProblemBasicVO.class);
        return new UnifiedResponse(paginationVO);
    }

    /**
     * 创建一个Problem
     *
     * @param problemDTO 问题信息
     * @author yuzhanglong
     * @date 2020-7-21 17:42:13
     */
    @PostMapping("/create_problem")
    @AuthorizationRequired(permission = PermissionEnum.PROBLEM_MANAGER)
    public UnifiedResponse createProblem(@RequestBody @Validated ProblemDTO problemDTO) {
        problemService.createProblem(problemDTO);
        return new UnifiedResponse();
    }

    /**
     * 编辑一个Problem
     *
     * @param problemDTO 问题信息
     * @param problemId  目标问题id
     * @author yuzhanglong
     * @date 2020-7-21 17:42:13
     */
    @PutMapping("/edit_problem/{problemId}")
    @AuthorizationRequired(permission = PermissionEnum.PROBLEM_MANAGER)
    public UnifiedResponse editProblem(
            @PathVariable Long problemId,
            @RequestBody @Validated ProblemDTO problemDTO) {
        problemService.editProblem(problemId, problemDTO);
        return new UnifiedResponse("编辑题目信息成功");
    }

    /**
     * 删除一个problem
     *
     * @param problemId 目标问题id
     * @author yuzhanglong
     * @date 2020-7-22
     */
    @DeleteMapping("/problem/{problemId}")
    @AuthorizationRequired(permission = PermissionEnum.PROBLEM_MANAGER)
    public UnifiedResponse deleteProblem(@PathVariable Long problemId) {
        problemService.deleteProblem(problemId);
        return new UnifiedResponse("删除问题成功");
    }


    /**
     * 获取某个problem的所有解决方案
     *
     * @param problemId 目标问题id
     * @author yuzhanglong
     * @date 2020-7-22
     */
    @GetMapping("/get_solutions/{problemId}")
    @AuthorizationRequired
    public UnifiedResponse getProblemSolutions(@PathVariable Long problemId) {
        List<JudgeSolutionEntity> solutionEntityList = problemService.getProblemSolutions(problemId);
        EntityToVoListMapper<JudgeSolutionEntity, SolutionVO> mapper = new EntityToVoListMapper<>(solutionEntityList, SolutionVO.class);
        return new UnifiedResponse(mapper.getItems());
    }

    /**
     * 获取某个problem的所有解决方案
     *
     * @param problemId 目标问题id
     * @author yuzhanglong
     * @date 2020-7-22
     */
    @PutMapping("/create_solution/{problemId}")
    @AuthorizationRequired(permission = PermissionEnum.PROBLEM_MANAGER)
    public UnifiedResponse editProblemSolutions(
            @PathVariable Long problemId,
            @Validated @RequestBody @NotNull SolutionDTO solution) {
        problemService.createSolution(problemId, solution);
        return new UnifiedResponse();
    }


    /**
     * 删除某个解决方案（测试点）
     *
     * @author yuzhanglong
     * @date 2020-8-21 18:35:23
     */
    @DeleteMapping("/delete_solution/{solutionId}")
    @AuthorizationRequired(permission = PermissionEnum.PROBLEM_MANAGER)
    public UnifiedResponse deleteSolution(@PathVariable Long solutionId) {
        problemService.deleteSolution(solutionId);
        return new UnifiedResponse("删除解决方案成功");
    }


    /**
     * 关闭某个problem
     *
     * @param problemId 目标problemId
     * @author yuzhanglong
     * @date 2020-7-26
     */
    @GetMapping("/close_problem/{problemId}")
    @AuthorizationRequired(permission = PermissionEnum.PROBLEM_MANAGER)
    public UnifiedResponse closeProblem(@PathVariable Long problemId) {
        problemService.closeProblem(problemId);
        return new UnifiedResponse();
    }

    /**
     * 修改Problem限制
     *
     * @param problemId 目标problemId
     * @author yuzhanglong
     * @date 2020-7-26 17:54:45
     */
    @PutMapping("/set_limitation/{problemId}")
    @AuthorizationRequired(permission = PermissionEnum.PROBLEM_MANAGER)
    public UnifiedResponse setLimitation(
            @PathVariable Long problemId,
            @RequestBody @Validated ProblemLimitationDTO limitation) {
        problemService.setLimitation(problemId, limitation);
        return new UnifiedResponse("修改题目限制成功");
    }


    /**
     * 获取最新的若干个problem
     *
     * @author yuzhanglong
     * @date 2020-8-30 13:36:05
     */
    @GetMapping("/get_recent_problem")
    @AuthorizationRequired
    public UnifiedResponse getRecentProblem(@RequestParam Integer size) {
        List<JudgeProblemEntity> res = problemService.getRecentProblem(size);
        EntityToVoListMapper<JudgeProblemEntity, ProblemBasicVO> mapper = new EntityToVoListMapper<>(res, ProblemBasicVO.class);
        List<ProblemBasicVO> problemBasicVOList = mapper.getItems();
        return new UnifiedResponse(problemBasicVOList);
    }

    /**
     * 获取用户AC过的问题
     *
     * @param uid 用户ID, 如果没有传则为调用者的信息
     * @author yuzhanglong
     * @date 2020-9-5 17:51:24
     */
    @GetMapping("/user_ac_problem_ids")
    @AuthorizationRequired
    public UnifiedResponse getUserAcceptProblems(@RequestParam(defaultValue = "") Long uid) {
        if (uid == null) {
            uid = UserHolder.getUserId();
        }
        List<Map<String, Object>> res = problemService.getUserAcceptProblems(uid);
        return new UnifiedResponse(res);
    }

    /**
     * 获取用户尝试过的问题(没通过)
     *
     * @param uid 用户ID, 如果没有传则为调用者的信息
     * @author yuzhanglong
     * @date 2020-9-5 17:51:27
     */
    @GetMapping("/user_tried_problem_ids")
    @AuthorizationRequired
    public UnifiedResponse getUserTriedProblems(@RequestParam(defaultValue = "") Long uid) {
        if (uid == null) {
            uid = UserHolder.getUserId();
        }
        List<Map<String, Object>> res = problemService.getUserTriedProblems(uid);
        return new UnifiedResponse(res);
    }
}