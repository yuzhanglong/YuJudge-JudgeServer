package com.yzl.yujudge.api.v1;

import com.yzl.yujudge.core.common.UnifiedResponse;
import com.yzl.yujudge.dto.ProblemDTO;
import com.yzl.yujudge.model.JudgeProblemEntity;
import com.yzl.yujudge.service.ProblemService;
import com.yzl.yujudge.vo.PaginationVO;
import com.yzl.yujudge.vo.ProblemBasicVO;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
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
     * @param start 从第几条记录开始获取内容
     * @param count 获取数据的数量
     * @author yuzhanglong
     * @description 以分页的方式获取problem内容
     * @date 2020-7-20 10:48:34
     */
    @GetMapping("/get_problems")
    public UnifiedResponse getProblems(
            @RequestParam(defaultValue = "0") Integer start,
            @RequestParam(defaultValue = "10") Integer count) {
        Page<JudgeProblemEntity> problems = problemService.getProblems(start, count);
        PaginationVO<JudgeProblemEntity> paginationVO = new PaginationVO<>(problems);
        List<JudgeProblemEntity> items = paginationVO.getItems();
        List<ProblemBasicVO> result = new ArrayList<>();
        //TODO: 封装下面这块遍历的代码,因为不止一处需要用到分页对象
        items.forEach(res -> {
            ProblemBasicVO tmp = new ProblemBasicVO();
            BeanUtils.copyProperties(res, tmp);
            result.add(tmp);
        });
        return new UnifiedResponse(result);
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
    @PostMapping("/edit_problem/{problemId}")
    public UnifiedResponse editProblem(
            @PathVariable Long problemId,
            @RequestBody @Validated ProblemDTO problemDTO) {
        problemService.editProblem(problemId, problemDTO);
        return new UnifiedResponse();
    }
}
