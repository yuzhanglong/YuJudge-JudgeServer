package com.yzl.yujudge.core.init;

import com.yzl.yujudge.core.enumeration.PermissionEnum;
import com.yzl.yujudge.model.PermissionEntity;
import com.yzl.yujudge.repository.PermissionRepository;
import com.yzl.yujudge.service.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


/**
 * 项目启动初始化 -- 权限写入
 *
 * @author yuzhanglong
 * @date 2020-8-30 12:18:15
 */

@Component
public class PermissionInitial implements ApplicationRunner {
    private static final Logger LOG = LoggerFactory.getLogger(PermissionInitial.class);

    private final PermissionService permissionService;
    private final PermissionRepository permissionRepository;

    public PermissionInitial(PermissionRepository permissionRepository, PermissionService permissionService) {
        this.permissionRepository = permissionRepository;
        this.permissionService = permissionService;
    }

    /**
     * 遍历全局权限枚举类, 向数据库中写入权限
     *
     * @author yuzhanglong
     * @date 2020-8-30 12:18:15
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (PermissionEnum value : PermissionEnum.values()) {
            PermissionEntity permissionEntity = permissionRepository.findByName(value.name());
            if (permissionEntity == null) {
                permissionService.createPermission(value.name(), value.getDescription());
            }
        }
    }
}
