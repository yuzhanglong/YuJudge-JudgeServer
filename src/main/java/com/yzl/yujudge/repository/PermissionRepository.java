package com.yzl.yujudge.repository;

import com.yzl.yujudge.model.PermissionEntity;

/**
 * 权限相关查询接口
 *
 * @author yuzhanglong
 * @date 2020-8-23 11:08:54
 */
public interface PermissionRepository extends BaseRepository<PermissionEntity> {

    /**
     * 通过id寻找相关权限
     *
     * @param permissionId 权限id
     * @return PermissionEntity实体类
     * @author yuzhanglong
     * @date 2020-8-23 13:15:34
     */
    PermissionEntity findOneById(Long permissionId);
}
