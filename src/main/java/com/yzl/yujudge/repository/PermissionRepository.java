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

    /**
     * 通过名称寻找相关权限
     *
     * @param name 权限名称
     * @return PermissionEntity实体类
     * @author yuzhanglong
     * @date 2020-8-30 12:38:31
     */
    PermissionEntity findByName(String name);


}
