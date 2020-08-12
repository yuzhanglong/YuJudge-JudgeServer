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

    @Override
    public String toString() {
        return "ScoreBoardBO{" +
                "isFrozen=" + isFrozen +
                ", participants=" + participants +
                '}';
    }
}
