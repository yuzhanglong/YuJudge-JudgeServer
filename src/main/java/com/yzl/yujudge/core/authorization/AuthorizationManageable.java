package com.yzl.yujudge.core.authorization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局权限管理相关接口
 *
 * @author yuzhanglong
 * @date 2020-8-22 22:49:25
 */
public interface AuthorizationManageable {

    /**
     * 非开放接口接口登录管理
     *
     * @param request  请求
     * @param response 响应
     * @return 支付正常执行完成
     * @author yuzhanglong
     * @date 2020-8-22 23:29:49
     */
    Boolean handleLogin(HttpServletRequest request, HttpServletResponse response);

    /**
     * 非开放接口接口登录管理, 其中权限范围更精细，与用户组相关联
     *
     * @param request    请求
     * @param response   响应
     * @param permission 这个接口的权限
     * @return 支付正常执行完成
     * @author yuzhanglong
     * @date 2020-8-23 00:02:34
     */
    Boolean handleUserGroupPermission(HttpServletRequest request, HttpServletResponse response, String permission);
}
