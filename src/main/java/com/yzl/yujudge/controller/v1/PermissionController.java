package com.yzl.yujudge.controller.v1;

import com.yzl.yujudge.core.common.UnifiedResponse;
import com.yzl.yujudge.dto.PermissionEditDTO;
import com.yzl.yujudge.service.PermissionService;
import com.yzl.yujudge.vo.PermissionVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 权限相关控制层
 *
 * @author yuzhanglong
 * @date 2020-8-23 11:06:28
 */

@RestController
@Validated
@RequestMapping("/permission")
public class PermissionController {
    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    /**
     * 获取所有可分配的权限
     *
     * @author yuzhanglong
     * @date 2020-8-23 11:08:08
     */
    @GetMapping("/get_permissions")
    public UnifiedResponse getPermissions() {
        List<PermissionVO> permissionVOList = permissionService.getPermissions();
        return new UnifiedResponse(permissionVOList);
    }

    /**
     * 获取用户组下的权限
     *
     * @author yuzhanglong
     * @date 2020-8-23 13:09:38
     */
    @GetMapping("/get_permission_by_user_group/{userGroupId}")
    public UnifiedResponse getPermissionsByUserGroup(@PathVariable Long userGroupId) {
        List<PermissionVO> res = permissionService.getPermissionsByUserGroup(userGroupId);
        return new UnifiedResponse(res);
    }

    /**
     * 修改用户组下的权限
     *
     * @author yuzhanglong
     * @date 2020-8-23 13:10:00
     */
    @PutMapping("/edit_user_group_permission/{userGroupId}")
    public UnifiedResponse editUserGroupPermission(
            @PathVariable Long userGroupId,
            @RequestBody PermissionEditDTO permissionEditDTO) {
        List<Long> permissions = permissionEditDTO.getPermissions();
        permissionService.editUserGroupPermission(userGroupId, permissions);
        return new UnifiedResponse("编辑成功~");
    }
}