package com.yzl.yujudge.dto;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 向用户组添加用户
 *
 * @author yuzhanglong
 * @date 2020-9-6 21:10:57
 */
public class AddUserToUserGroupDTO {
    @NotNull(message = "用户id不得为空")
    private List<Long> userIds;

    @NotNull(message = "用户组id不得为空")
    private Long userGroupId;

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }

    public Long getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Long userGroupId) {
        this.userGroupId = userGroupId;
    }

    @Override
    public String toString() {
        return "AddUserToUserGroupDTO{" +
                "userIds=" + userIds +
                ", userGroupId=" + userGroupId +
                '}';
    }
}
