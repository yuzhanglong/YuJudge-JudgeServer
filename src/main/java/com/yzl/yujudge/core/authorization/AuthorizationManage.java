package com.yzl.yujudge.core.authorization;

import com.auth0.jwt.interfaces.Claim;
import com.yzl.yujudge.core.configuration.AuthorizationConfiguration;
import com.yzl.yujudge.core.exception.http.ForbiddenException;
import com.yzl.yujudge.service.UserService;
import com.yzl.yujudge.utils.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 权限管理的业务逻辑
 *
 * @author yuzhanglong
 * @date 2020-8-22 23:29:30
 */
@Component
public class AuthorizationManage implements AuthorizationManageable {

    private final AuthorizationConfiguration authorizationConfiguration;
    private final UserService userService;
    public static final String AUTHORIZATION_HEADER_NAME = "Authorization";

    public AuthorizationManage(AuthorizationConfiguration authorizationConfiguration, UserService userService) {
        this.authorizationConfiguration = authorizationConfiguration;
        this.userService = userService;
    }

    /**
     * 非开放接口接口登录管理
     *
     * @param request  请求
     * @param response 响应
     * @author yuzhanglong
     * @date 2020-8-22 23:29:49
     */
    @Override
    public Boolean handleLogin(HttpServletRequest request, HttpServletResponse response) {
        String accessToken = checkHeaderAndGetToken(request);
        String secret = authorizationConfiguration.getSecretKey();
        Map<String, Claim> userInfo = TokenUtil.checkAuthToken(accessToken, secret);
        if (userInfo == null) {
            throw new ForbiddenException("A0004");
        }
        setUserDataToThreadLocal(userInfo);
        return true;
    }

    @Override
    public Boolean handleUserGroupPermission(HttpServletRequest request, HttpServletResponse response, String permission) {
        handleLogin(request, response);
        Long userId = UserHolder.getUserId();
        // 超级管理员免检
        if (isRootUser(userId)) {
            return true;
        }
        return userService.isUserPermissionAccepted(userId, permission);
    }

    /**
     * Authorization 尝试从请求头中读取token
     *
     * @param request 请求
     * @return token字符串
     * @author yuzhanglong
     * @date 2020-8-22 23:42:30
     */
    private String checkHeaderAndGetToken(HttpServletRequest request) {
        // 获取请求头
        String accessToken = request.getHeader(AUTHORIZATION_HEADER_NAME);
        // 非正常情况判断
        if (accessToken == null || StringUtils.isEmpty(accessToken)) {
            throw new ForbiddenException("A0003");
        }
        return accessToken;
    }

    /**
     * 向全局用户ThreadLocal添加用户信息
     *
     * @author yuzhanglong
     * @date 2020-08-07 11:09:04
     * @description
     */
    private void setUserDataToThreadLocal(Map<String, Claim> userInfo) {
        String userId = userInfo.get("userId").asString();
        UserHolder.setUser(Long.valueOf(userId));
    }

    /**
     * 判断用户是否为超级管理员(ROOT用户组)
     *
     * @author yuzhanglong
     * @date 2020-08-07 11:09:04
     */
    private Boolean isRootUser(Long userId) {
        return userService.isRootUser(userId);
    }
}
