package com.yzl.yujudge.core.configuration;

import com.yzl.yujudge.core.factory.YamlPropertySourceFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 权限相关配置类
 *
 * @author yuzhanglong
 * @date 2020-08-03 16:51:26
 */

@Component
@ConfigurationProperties(prefix = "authorization")
@PropertySource(value = "classpath:application.yml", factory = YamlPropertySourceFactory.class)
public class AuthorizationConfiguration {
    private Integer expiredIn;
    private String secretKey;

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Integer getExpiredIn() {
        return expiredIn;
    }

    public void setExpiredIn(Integer expiredIn) {
        this.expiredIn = expiredIn;
    }
}
