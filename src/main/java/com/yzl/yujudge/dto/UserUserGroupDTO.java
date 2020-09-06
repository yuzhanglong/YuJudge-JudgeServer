package com.yzl.yujudge.dto;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 用户 -- 多个用户组视图层对象
 *
 * @author yuzhanglong
 * @date 2020-9-6 21:37:25
 */
public class UserUserGroupDTO {
    @NotNull(message = "用户id不得为空")
    private Long userId;
    @NotNull(message = "用户组id不得为空")
    private List<Long> userGroupIds;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getUserGroupIds() {
        return userGroupIds;
    }

    public void setUserGroupIds(List<Long> userGroupIds) {
        this.userGroupIds = userGroupIds;
    }

    @Override
    public String toString() {
        return "UserUserGroupDTO{" +
                "userId=" + userId +
                ", userGroupIds=" + userGroupIds +
                '}';
    }
}
