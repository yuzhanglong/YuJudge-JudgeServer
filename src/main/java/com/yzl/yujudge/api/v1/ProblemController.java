package com.yzl.yujudge.api.v1;

import com.yzl.yujudge.model.JudgeProblemEntity;
import com.yzl.yujudge.service.ProblemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuzhanglong
 * @description 题目相关控制层
 * @date 2020-7-18 04:26
 */
@RestController
@RequestMapping("/problem")
public class ProblemController {
    private final ProblemService problemService;

    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    /**
     * @author yuzhanglong
     * @description 获取题目信息的结构，调用者传入一个problem id，
     * 我们通过这个id找到problem信息并返回给调用者
     * @date 2020-7-18 04:26
     */
    @GetMapping("/get_problem_by_id/{problemId}")
    public JudgeProblemEntity getProblemInfoById(@PathVariable Long problemId) {
        JudgeProblemEntity problem = problemService.getProblemInfoById(problemId);
        return problem;
    }
}
