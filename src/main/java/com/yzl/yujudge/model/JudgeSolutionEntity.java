package com.yzl.yujudge.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 解决方案实体类
 *
 * @author yuzhanglong
 * @date 2020-7-18 23:50:19
 */

@Entity
@DynamicUpdate
@DynamicInsert
@Where(clause = "delete_time is null")
@Table(name = "judge_solution", schema = "yu-judge")
public class JudgeSolutionEntity extends SoftDeleteEntity {
    @Basic
    @Column(name = "std_in")
    private String stdIn;

    @Basic
    @Column(name = "expected_std_out")
    private String expectedStdOut;

    @Basic
    @Column(name = "pk_problem")
    private Long pkProblem;

    @Basic
    @Column(name = "description")
    private String description;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
