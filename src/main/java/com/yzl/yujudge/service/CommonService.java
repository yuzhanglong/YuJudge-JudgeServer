package com.yzl.yujudge.service;

import com.qiniu.util.Auth;
import com.yzl.yujudge.core.configuration.UploadConfiguration;
import org.springframework.stereotype.Service;

/**
 * @author yuzhanglong
 * @description 一般性业务逻辑
 * @date 2020-08-05 20:44:04
 */

@Service
public class CommonService {
    private final UploadConfiguration uploadConfiguration;

    public CommonService(UploadConfiguration uploadConfiguration) {
        this.uploadConfiguration = uploadConfiguration;
    }

    /**
     * @author yuzhanglong
     * @description 获取上传凭证
     * @date 2020-08-06 12:47:19
     */
    public String getUploadToken() {
        String accessKey = uploadConfiguration.getAccessKey();
        String secretKey = uploadConfiguration.getSecretKey();
        String bucketName = uploadConfiguration.getBucket();
        Auth auth = Auth.create(accessKey, secretKey);
        return auth.uploadToken(bucketName);
    }
}