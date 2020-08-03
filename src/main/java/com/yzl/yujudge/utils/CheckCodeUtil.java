package com.yzl.yujudge.utils;

import com.wf.captcha.SpecCaptcha;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhanglong
 * @description 验证码相关工具类，基于easy-captcha包的二次封装
 * 其中: 常量 xxxKEY_NAME 代表了该内容对应的键名
 * @date 2020-08-03 19:58:51
 */

public class CheckCodeUtil {
    private static final int CHECK_CODE_WIDTH = 130;
    private static final int CHECK_CODE_HEIGHT = 48;
    private static final int CHECK_CODE_LENGTH = 5;
    public static final String CODE_IMAGE_KEY_NAME = "image";
    public static final String CODE_CONTENT_KEY_NAME = "content";
    public static final String CODE_KEY_KEY_NAME = "key";

    /**
     * @return Map<String, String> 生成验证码的信息
     * 内容包括: 验证码的base64、验证码的内容
     * @author yuzhanglong
     * @description 生成验证码，并获取其base64, 内容信息
     * 我们以map的形式返回供后续使用，
     * 例如取文本内容，生成对应的key存到redis供认证、将图片base64返回给前端等
     * @date 2020-08-03 20:09:27
     */

    public static Map<String, String> getCheckCode() {
        SpecCaptcha specCaptcha = new SpecCaptcha(CHECK_CODE_WIDTH, CHECK_CODE_HEIGHT, CHECK_CODE_LENGTH);
        String content = specCaptcha.text().toLowerCase();
        Map<String, String> info = new HashMap<>(2);
        info.put(CODE_IMAGE_KEY_NAME, specCaptcha.toBase64());
        info.put(CODE_CONTENT_KEY_NAME, content);
        info.put(CODE_KEY_KEY_NAME, null);
        return info;
    }
}
