package com.yzl.yujudge;

import com.yzl.yujudge.dto.ProblemDTO;
import com.yzl.yujudge.dto.SolutionDTO;
import com.yzl.yujudge.model.JudgeProblemEntity;
import com.yzl.yujudge.service.ProblemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProblemTest {
    @Autowired
    private ProblemService problemService;


    @Test
    void testCreateProblem() {
        ProblemDTO problemDTO = new ProblemDTO();
        for (int i = 0; i < 10; i++) {
            String name = "测试问题" + i;
            if (problemService.getProblemByName(name) != null) {
                problemDTO.setName(name);
                problemService.createProblem(problemDTO);
            }
        }
    }

    @Test
    void testAddSolution() {
        for (int i = 0; i < 10; i++) {
            String name = "测试问题" + i;
            JudgeProblemEntity problemEntity = problemService.getProblemByName(name);
            SolutionDTO solutionDTO = new SolutionDTO();
            // A + B
            solutionDTO.setStdIn("http://cdn.yuzzl.top/1596680709217.in");
            solutionDTO.setExpectedStdOut("http://cdn.yuzzl.top/1596680709217.out");
            problemService.createSolution(problemEntity.getId(), solutionDTO);
        }
    }
}
