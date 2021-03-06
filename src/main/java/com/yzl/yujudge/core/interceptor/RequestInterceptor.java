package com.yzl.yujudge.core.interceptor;


import com.yzl.yujudge.core.authorization.AuthorizationManageable;
import com.yzl.yujudge.core.authorization.AuthorizationRequired;
import com.yzl.yujudge.core.authorization.UserHolder;
import com.yzl.yujudge.core.configuration.AuthorizationConfiguration;
import com.yzl.yujudge.core.enumeration.PermissionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * springboot控制层拦截配置
 *
 * @author yuzhanglong
 * @date 2020-08-03 19:30:46
 */
public class RequestInterceptor implements HandlerInterceptor {

    @Autowired
    private AuthorizationManageable authorizationManageable;

    @Autowired
    private AuthorizationConfiguration authorizationConfiguration;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //  如果不是反射到方法
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        AuthorizationRequired authorizationRequired = method.getAnnotation(AuthorizationRequired.class);
        if (authorizationRequired != null && authorizationConfiguration.getActive()) {
            // 由 AuthorizationManage 处理权限相关
            if (authorizationRequired.permission() != PermissionEnum.ANY) {
                return authorizationManageable.handleUserGroupPermission(request, response, authorizationRequired.permission());
            }
            return authorizationManageable.handleLogin(request, response);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        UserHolder.remove();
    }
}