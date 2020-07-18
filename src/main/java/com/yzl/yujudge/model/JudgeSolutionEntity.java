package com.yzl.yujudge.model;

import javax.persistence.*;

/**
 * @author yuzhanglong
 * @description 解决方案实体类
 * @date 2020-7-18 23:50:19
 */

@Entity
@Table(name = "judge_solution", schema = "yu-judge")
public class JudgeSolutionEntity extends BaseEntity {
    private Long id;
    private String stdIn;
    private String expectedStdOut;
    private Long pkProblem;

    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "std_in")
    public String getStdIn() {
        return stdIn;
    }

    public void setStdIn(String stdIn) {
        this.stdIn = stdIn;
    }

    @Basic
    @Column(name = "expected_std_out")
    public String getExpectedStdOut() {
        return expectedStdOut;
    }

    public void setExpectedStdOut(String expectedStdOut) {
        this.expectedStdOut = expectedStdOut;
    }

    @Basic
    @Column(name = "pk_problem")
    public Long getPkProblem() {
        return pkProblem;
    }

    public void setPkProblem(Long pkProblem) {
        this.pkProblem = pkProblem;
    }
}
