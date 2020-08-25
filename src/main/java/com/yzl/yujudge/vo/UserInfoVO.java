package com.yzl.yujudge.vo;

import java.util.List;

/**
 * @author yuzhanglong
 * @description 用户详细信息的视图层对象
 * @date 2020-08-07 20:28:13
 */
public class UserInfoVO extends UserInfoBasicVO {
    private List<UserGroupVO> userGroups;

    public List<UserGroupVO> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(List<UserGroupVO> userGroups) {
        this.userGroups = userGroups;
    }

    @Override
    public String toString() {
        return "UserInfoVO{" +
                "userGroups=" + userGroups +
                '}';
    }
}