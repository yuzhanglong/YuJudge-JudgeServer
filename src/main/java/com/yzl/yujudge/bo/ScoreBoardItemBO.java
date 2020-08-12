package com.yzl.yujudge.bo;

import com.yzl.yujudge.vo.UserInfoVO;

import java.util.List;
import java.util.Map;

/**
 * @author yuzhanglong
 * @description 记分板子项的业务对象
 * @date 2020-08-12 23:14:38
 */

public class ScoreBoardItemBO {
    private List<Map<String, Object>> solutionInfo;
    private UserInfoVO teamInfo;

    public List<Map<String, Object>> getSolutionInfo() {
        return solutionInfo;
    }

    public void setSolutionInfo(List<Map<String, Object>> solutionInfo) {
        this.solutionInfo = solutionInfo;
    }

    public UserInfoVO getTeamInfo() {
        return teamInfo;
    }

    public void setTeamInfo(UserInfoVO teamInfo) {
        this.teamInfo = teamInfo;
    }

    @Override
    public String toString() {
        return "ScoreBoardItemBO{" +
                "solutionInfo=" + solutionInfo +
                ", teamInfo=" + teamInfo +
                '}';
    }
}
