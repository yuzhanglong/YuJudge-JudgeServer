package com.yzl.yujudge.controller.v1;

import com.yzl.yujudge.core.common.UnifiedResponse;
import com.yzl.yujudge.dto.UserGroupDTO;
import com.yzl.yujudge.service.UserGroupService;
import com.yzl.yujudge.vo.UserGroupVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yuzhanglong
 * @description 用户组相关接口
 * @date 2020-08-16 12:46:26
 */

@RestController
@Validated
@CrossOrigin
@RequestMapping("/user_group")
public class UserGroupController {
    private final UserGroupService userGroupService;

    public UserGroupController(UserGroupService userGroupService) {
        this.userGroupService = userGroupService;
    }

    /**
     * 创建用户组
     *
     * @param userGroupDTO 用户组相关数据传输对象
     * @author yuzhanglong
     * @date 2020-8-22 14:18:22
     */
    @PostMapping("/create_user_group")
    public UnifiedResponse createUserGroup(@Validated @RequestBody UserGroupDTO userGroupDTO) {
        userGroupService.createUserGroup(userGroupDTO);
        return new UnifiedResponse("创建用户组成功");
    }

    /**
     * 获取用户组信息
     *
     * @author yuzhanglong
     * @date 2020-8-22 14:41:51
     */
    @GetMapping("/get_user_groups")
    public UnifiedResponse getUserGroup() {
        List<UserGroupVO> userGroupEntities = userGroupService.getUserGroups();
        return new UnifiedResponse(userGroupEntities);
    }

    /**
     * 删除用户组
     *
     * @author yuzhanglong
     * @date 2020-8-22 14:41:51
     */
    @DeleteMapping("/delete_user_group/{userGroupId}")
    public UnifiedResponse deleteUserGroup(@PathVariable Long userGroupId) {
        userGroupService.deleteUserGroup(userGroupId);
        return new UnifiedResponse("删除用户组成功");
    }

    /**
     * 编辑用户组
     *
     * @param userGroupDTO 用户组相关数据传输对象
     * @author yuzhanglong
     * @date 2020-8-22 15:06:23
     */
    @PutMapping("/edit_user_group/{userGroupId}")
    public UnifiedResponse editUserGroup(
            @PathVariable Long userGroupId,
            @RequestBody UserGroupDTO userGroupDTO) {
        userGroupService.setUserGroup(userGroupId, userGroupDTO);
        return new UnifiedResponse("编辑用户组信息成功");
    }
}
