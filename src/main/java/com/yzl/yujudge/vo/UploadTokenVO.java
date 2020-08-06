package com.yzl.yujudge.vo;

/**
 * @author yuzhanglong
 * @description 获取上传凭证的视图层对象
 * @date 2020-08-05 20:59:07
 */
public class UploadTokenVO {
    private String uploadToken;


    public String getUploadToken() {
        return uploadToken;
    }

    public void setUploadToken(String uploadToken) {
        this.uploadToken = uploadToken;
    }

    @Override
    public String toString() {
        return "UploadTokenVO{" +
                "uploadToken='" + uploadToken + '\'' +
                '}';
    }
}
