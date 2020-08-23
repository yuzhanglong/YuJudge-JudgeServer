package com.yzl.yujudge.service;

import com.yzl.yujudge.core.exception.http.NotFoundException;
import com.yzl.yujudge.model.PermissionEntity;
import com.yzl.yujudge.model.UserGroupEntity;
import com.yzl.yujudge.repository.PermissionRepository;
import com.yzl.yujudge.repository.UserGroupRepository;
import com.yzl.yujudge.utils.EntityToVoListMapper;
import com.yzl.yujudge.vo.PermissionVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限相关业务逻辑
 *
 * @author yuzhanglong
 * @date 2020-8-23 11:16:23
 */

@Service
public class PermissionService {
    private final PermissionRepository permissionRepository;
    private final UserGroupRepository userGroupRepository;
    private final UserGroupService userGroupService;

    public PermissionService(
            PermissionRepository permissionRepository,
            UserGroupRepository userGroupRepository,
            UserGroupService userGroupService) {
        this.permissionRepository = permissionRepository;
        this.userGroupRepository = userGroupRepository;
        this.userGroupService = userGroupService;
    }


    /**
     * 获取所有可分配的权限
     *
     * @author yuzhanglong
     * @date 2020-8-23 11:23:36
     */
    public List<PermissionVO> getPermissions() {
        List<PermissionEntity> permissionEntityList = permissionRepository.findAll();
        EntityToVoListMapper<PermissionEntity, PermissionVO> mapper = new EntityToVoListMapper<>(permissionEntityList, PermissionVO.class);
        return mapper.getItems();
    }

    /**
     * 通过用户组id，获取该用户组下所有的权限
     *
     * @author yuzhanglong
     * @date 2020-8-23 12:16:44
     */
    public List<PermissionVO> getPermissionsByUserGroup(Long userGroupId) {
        UserGroupEntity userGroupEntity = userGroupRepository.findOneById(userGroupId);
        if (userGroupEntity == null) {
            throw new NotFoundException("B0015");
        }
        List<PermissionEntity> permissionEntityList = userGroupEntity.getPermissions();
        EntityToVoListMapper<PermissionEntity, PermissionVO> mapper = new EntityToVoListMapper<>(permissionEntityList, PermissionVO.class);
        return mapper.getItems();
    }

    /**
     * 修改该用户组的权限
     *
     * @param userGroupId 用户组id
     * @param permissions 需要授权的权限id列表
     * @author yuzhanglong
     * @date 2020-8-23 12:16:44
     */
    public void editUserGroupPermission(Long userGroupId, List<Long> permissions) {
        UserGroupEntity userGroupEntity = userGroupRepository.findOneById(userGroupId);
        if (userGroupEntity == null) {
            throw new NotFoundException("B0015");
        }
        // 禁止修改默认用户组
        userGroupService.validateUserGroupName(userGroupEntity.getName());

        List<PermissionEntity> permissionEntityList = new ArrayList<>();
        for (Long permission : permissions) {
            PermissionEntity permissionEntity = permissionRepository.findOneById(permission);
            if (permissionEntity == null) {
                throw new NotFoundException("B0018");
            }
            permissionEntityList.add(permissionEntity);
        }
        userGroupEntity.setPermissions(permissionEntityList);
        userGroupRepository.save(userGroupEntity);
    }
}
