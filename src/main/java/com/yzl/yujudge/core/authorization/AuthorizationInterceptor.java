package com.yzl.yujudge.core.authorization;


import com.yzl.yujudge.core.configuration.AuthorizationConfiguration;
import com.yzl.yujudge.core.exception.http.ForbiddenException;
import com.yzl.yujudge.utils.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author yuzhanglong
 * @date 2020-08-03 19:30:46
 * @description 权限验证器类
 */
@EnableConfigurationProperties({AuthorizationConfiguration.class})
public class AuthorizationInterceptor implements HandlerInterceptor {
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
        // 寻找 AuthorizationRequired注解
        AuthorizationRequired authorization = method.getAnnotation(AuthorizationRequired.class);
        if (authorization != null) {
            // 获取、解码accessToken
            String accessToken = request.getHeader("Authorization");
            // accessToken为空
            if (StringUtils.isEmpty(accessToken)) {
                throw new ForbiddenException("A0003");
            }
            String secret = authorizationConfiguration.getSecretKey();
            Boolean isPass = TokenUtil.checkAuthToken(accessToken, secret);
            if (!isPass) {
                throw new ForbiddenException("A0004");
            }
        }
        return true;
    }
}
