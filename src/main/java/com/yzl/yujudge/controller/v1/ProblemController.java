package com.yzl.yujudge.controller.v1;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.yzl.yujudge.core.common.UnifiedResponse;
import com.yzl.yujudge.dto.ProblemDTO;
import com.yzl.yujudge.dto.ProblemLimitationDTO;
import com.yzl.yujudge.dto.SolutionDTO;
import com.yzl.yujudge.model.JudgeProblemEntity;
import com.yzl.yujudge.model.JudgeSolutionEntity;
import com.yzl.yujudge.service.ProblemService;
import com.yzl.yujudge.utils.EntityToVoListMapper;
import com.yzl.yujudge.vo.PaginationVO;
import com.yzl.yujudge.vo.ProblemBasicVO;
import com.yzl.yujudge.vo.ProblemDetailVO;
import com.yzl.yujudge.vo.SolutionVO;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author yuzhanglong
 * @description 题目相关控制层
 * @date 2020-7-18 04:26
 */
@RestController
@Validated
@CrossOrigin
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
     * @param problemId 问题id
     * @author yuzhanglong
     * @description 获取题目信息的结构，调用者传入一个problem id，
     * 我们通过这个id找到problem信息并返回给调用者
     * @date 2020-7-18 04:26
     */
    @GetMapping("/get_problem_by_id/{problemId}")
    public UnifiedResponse getProblemInfoById(@PathVariable Long problemId) {
        JudgeProblemEntity problem = problemService.getProblemInfoById(problemId);
        ProblemBasicVO problemBasicInfo = new ProblemBasicVO();
        BeanUtils.copyProperties(problem, problemBasicInfo);
        return new UnifiedResponse(problemBasicInfo);
    }


    /**
     * @param problemId 问题id
     * @author yuzhanglong
     * @description 获取某个problem的详细信息
     * 我们通过这个id找到problem详细信息并返回给调用者
     * @date 2020-7-18 04:26
     */
    @GetMapping("/get_problem_detailed_by_id/{problemId}")
    public UnifiedResponse getProblemDetailedInfoById(@PathVariable Long problemId) {
        JudgeProblemEntity problem = problemService.getProblemInfoById(problemId);
        ProblemDetailVO problemDetailed = mapper.map(problem, ProblemDetailVO.class);
        return new UnifiedResponse(problemDetailed);
    }


    /**
     * @param start  从第几条记录开始获取内容
     * @param count  获取数据的数量
     * @param search 搜索关键字
     * @author yuzhanglong
     * @description 以分页的方式获取problem内容
     * @date 2020-7-20 10:48:34
     */
    @GetMapping("/get_problems")
    public UnifiedResponse getProblems(
            @RequestParam(defaultValue = "0") Integer start,
            @RequestParam(defaultValue = "10") Integer count,
            @RequestParam(defaultValue = "") String search) {
        Page<JudgeProblemEntity> problems = problemService.getProblems(start, count, search);
        PaginationVO<JudgeProblemEntity, ProblemBasicVO> paginationVO = new PaginationVO<>(problems, ProblemBasicVO.class);
        return new UnifiedResponse(paginationVO);
    }

    /**
     * @param problemDTO 问题信息
     * @author yuzhanglong
     * @description 创建一个Problem
     * @date 2020-7-21 17:42:13
     */
    @PostMapping("/create_problem")
    public UnifiedResponse createProblem(@RequestBody @Validated ProblemDTO problemDTO) {
        problemService.createProblem(problemDTO);
        return new UnifiedResponse();
    }

    /**
     * @param problemDTO 问题信息
     * @param problemId  目标问题id
     * @author yuzhanglong
     * @description 编辑一个Problem
     * @date 2020-7-21 17:42:13
     */
    @PutMapping("/edit_problem/{problemId}")
    public UnifiedResponse editProblem(
            @PathVariable Long problemId,
            @RequestBody @Validated ProblemDTO problemDTO) {
        problemService.editProblem(problemId, problemDTO);
        return new UnifiedResponse("编辑题目信息成功");
    }

    /**
     * @param problemId 目标问题id
     * @author yuzhanglong
     * @description 删除一个problem
     * @date 2020-7-22
     */
    @DeleteMapping("/delete_problem/{problemId}")
    public UnifiedResponse deleteProblem(@PathVariable Long problemId) {
        problemService.deleteProblem(problemId);
        return new UnifiedResponse();
    }


    /**
     * @param problemId 目标问题id
     * @author yuzhanglong
     * @description 获取某个problem的所有解决方案
     * @date 2020-7-22
     */
    @GetMapping("/get_solutions/{problemId}")
    public UnifiedResponse getProblemSolutions(@PathVariable Long problemId) {
        List<JudgeSolutionEntity> solutionEntityList = problemService.getProblemSolutions(problemId);
        EntityToVoListMapper<JudgeSolutionEntity, SolutionVO> mapper = new EntityToVoListMapper<>(solutionEntityList, SolutionVO.class);
        return new UnifiedResponse(mapper.getItems());
    }

    /**
     * @param problemId 目标问题id
     * @author yuzhanglong
     * @description 获取某个problem的所有解决方案
     * @date 2020-7-22
     */
    @PostMapping("/create_solution/{problemId}")
    public UnifiedResponse editProblemSolutions(
            @PathVariable Long problemId,
            @Validated @RequestBody @NotNull SolutionDTO solution) {
        problemService.createSolution(problemId, solution);
        return new UnifiedResponse();
    }


    /**
     * @param solutionId 目标solutionId
     * @author yuzhanglong
     * @description 删除某个solution
     * @date 2020-7-22
     */
    @DeleteMapping("/delete_solution/{solutionId}")
    public UnifiedResponse deleteSolution(@PathVariable Long solutionId) {
        problemService.deleteSolution(solutionId);
        return new UnifiedResponse();
    }


    /**
     * @param problemId 目标problemId
     * @author yuzhanglong
     * @description 关闭某个problem
     * @date 2020-7-26
     */
    @GetMapping("/close_problem/{problemId}")
    public UnifiedResponse closeProblem(@PathVariable Long problemId) {
        problemService.closeProblem(problemId);
        return new UnifiedResponse();
    }

    /**
     * @param problemId 目标problemId
     * @author yuzhanglong
     * @description 修改Problem限制
     * @date 2020-7-26 17:54:45
     */
    @PutMapping("/set_limitation/{problemId}")
    public UnifiedResponse setLimitation(
            @PathVariable Long problemId,
            @RequestBody @Validated ProblemLimitationDTO limitation) {
        problemService.setLimitation(problemId, limitation);
        return new UnifiedResponse("修改题目限制成功");
    }


    /**
     * @author yuzhanglong
     * @description 获取最新的若干个problem
     * @date 2020-08-06 20:14:45
     */
    @GetMapping("/get_recent_problem")
    public UnifiedResponse getRecentProblem(@RequestParam Integer size) {
        List<JudgeProblemEntity> res = problemService.getRecentProblem(size);
        EntityToVoListMapper<JudgeProblemEntity, ProblemBasicVO> mapper = new EntityToVoListMapper<>(res, ProblemBasicVO.class);
        List<ProblemBasicVO> problemBasicVOList = mapper.getItems();
        return new UnifiedResponse(problemBasicVOList);
    }
}