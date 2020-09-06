package com.yzl.yujudge.core.authorization;

import com.yzl.yujudge.core.enumeration.PermissionEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标示请求是否需要认证的注解
 *
 * @author yuzhanglong
 * @date 2020-7-2 8:38
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthorizationRequired {
    // 权限名称，在数据表中对应了permission的name，
    // 我们要做的判断在本质上就是【用户--权限的判断】
    PermissionEnum permission() default PermissionEnum.ANY;
}