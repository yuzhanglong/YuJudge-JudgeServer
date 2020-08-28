package com.yzl.yujudge.core.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web配置
 *
 * @author yuzhanglong
 * @date 2020-8-28 11:03:27
 */

@Configuration
public class WebServerConfiguration implements WebMvcConfigurer {
    @Value("${cross-origin}")
    private Boolean isAllowCrossOrigin;

    /**
     * 处理跨域
     *
     * @author yuzhanglong
     * @date 2020-8-28 11:09:20
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        if (isAllowCrossOrigin) {
            registry.addMapping("/**")
                    .allowedOrigins("*")
                    .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                    .maxAge(3600)
                    .allowCredentials(true);
        }
    }
}
