package com.yzl.yujudge.core.interceptor;


import com.yzl.yujudge.core.authorization.AuthorizationManageable;
import com.yzl.yujudge.core.authorization.AuthorizationRequired;
import com.yzl.yujudge.core.authorization.UserHolder;
import com.yzl.yujudge.core.configuration.AuthorizationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
@EnableConfigurationProperties({AuthorizationConfiguration.class})
public class RequestInterceptor implements HandlerInterceptor {

    @Autowired
    private AuthorizationManageable authorizationManageable;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //  如果不是反射到方法
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        AuthorizationRequired authorizationRequired = method.getAnnotation(AuthorizationRequired.class);
        if (authorizationRequired != null) {
            // 由 AuthorizationManage 处理权限相关
            if (!"".equals(authorizationRequired.permission())) {
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