package com.yzl.yujudge.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * @author yuzhanglong
 * @description 解决方案实体类
 * @date 2020-7-18 23:50:19
 */

@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "judge_solution", schema = "yu-judge")
public class JudgeSolutionEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "std_in")
    private String stdIn;

    @Basic
    @Column(name = "expected_std_out")
    private String expectedStdOut;

    @Basic
    @Column(name = "pk_problem")
    private Long pkProblem;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getStdIn() {
        return stdIn;
    }

    public void setStdIn(String stdIn) {
        this.stdIn = stdIn;
    }


    public String getExpectedStdOut() {
        return expectedStdOut;
    }

    public void setExpectedStdOut(String expectedStdOut) {
        this.expectedStdOut = expectedStdOut;
    }


    public Long getPkProblem() {
        return pkProblem;
    }

    public void setPkProblem(Long pkProblem) {
        this.pkProblem = pkProblem;
    }
}
