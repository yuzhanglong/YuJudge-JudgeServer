package com.yzl.yujudge.core.configuration;

import com.yzl.yujudge.core.factory.YamlPropertySourceFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author yuzhanglong
 * @date 2020-08-06 12:37:15
 * @description 上传相关配置
 */

@Component
@ConfigurationProperties(prefix = "upload.qn-service")
@PropertySource(value = "classpath:application.yml", factory = YamlPropertySourceFactory.class)
public class UploadConfiguration {
    private String accessKey;
    private String secretKey;
    private String bucket;


    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }
}
