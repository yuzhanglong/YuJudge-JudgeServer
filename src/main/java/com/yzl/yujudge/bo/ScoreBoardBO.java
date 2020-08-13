package com.yzl.yujudge.bo;

import java.util.List;

/**
 * @author yuzhanglong
 * @description 记分板的的业务对象
 * @date 2020-08-12 14:33:51
 */

public class ScoreBoardBO {
    private Boolean isFrozen;
    private List<ScoreBoardItemBO> participants;
    private Integer problemAmount;

    public ScoreBoardBO(Boolean isFrozen, List<ScoreBoardItemBO> participants, Integer problemAmount) {
        this.isFrozen = isFrozen;
        this.participants = participants;
        this.problemAmount = problemAmount;
    }

    public Boolean getFrozen() {
        return isFrozen;
    }

    public void setFrozen(Boolean frozen) {
        isFrozen = frozen;
    }

    public List<ScoreBoardItemBO> getParticipants() {
        return participants;
    }

    public void setParticipants(List<ScoreBoardItemBO> participants) {
        this.participants = participants;
    }

    public Integer getProblemAmount() {
        return problemAmount;
    }

    public void setProblemAmount(Integer problemAmount) {
        this.problemAmount = problemAmount;
    }

    @Override
    public String toString() {
        return "ScoreBoardBO{" +
                "isFrozen=" + isFrozen +
                ", participants=" + participants +
                '}';
    }
}
