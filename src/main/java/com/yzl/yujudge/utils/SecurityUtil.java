package com.yzl.yujudge.utils;

import org.springframework.util.DigestUtils;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author yuzhanglong
 * @description 鉴权相关工具类
 * @date 2020-08-03 14:34:22
 */

public class SecurityUtil {
    private static final String MAC_NAME = "HmacSHA1";
    private static final String ENCODING = "UTF-8";


    /**
     * @author yuzhanglong
     * @description 使用HMAC加密算法加密密码等隐私信息
     * @date 2020-08-03 14:37:49
     */
    public static String encodeSecretByHmac(String content, String salt) throws Exception {
        byte[] data = salt.getBytes(ENCODING);
        //根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
        SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
        //生成一个指定 Mac 算法 的 Mac 对象
        Mac mac = Mac.getInstance(MAC_NAME);
        //用给定密钥初始化 Mac 对象
        mac.init(secretKey);
        byte[] text = content.getBytes(ENCODING);
        byte[] res = mac.doFinal(text);
        StringBuilder stringBuilder = new StringBuilder();

        for (byte item : res) {
            stringBuilder.append(Integer.toHexString((item & 0xFF) | 0x100), 1, 3);
        }
        return stringBuilder.toString().toUpperCase();
    }

    /**
     * @param content 需要加密的字符串
     * @author yuzhanglong
     * @description 将字符串md5加密
     * @date 2020-08-03 16:04:09
     */
    public static String encodeSecretByMd5(String content) throws NullPointerException {
        if (content == null) {
            throw new NullPointerException("待加密字符串不得为空");
        }
        return DigestUtils.md5DigestAsHex(content.getBytes());
    }

    /**
     * @param password 需要加密的密码
     * @author yuzhanglong
     * @description 加密密码，以供后续使用或者持久化存储
     * @date 2020-08-03 16:07:06
     */
    public static String generatePasswordHash(String password) {
        return encodeSecretByMd5(password);
    }

    /**
     * @param passwordToCheck 待检测密码
     * @param passwordHashed  加密后的密码
     * @return Boolean 密码验证是否通过
     * @author yuzhanglong
     * @description 检测密码是否正确
     * 通过将待检测的密码进行md5加密，再和数据库中取出的hash过的密码进行比较
     * 如果两者相等，则通过
     * @date 2020-08-03 16:08:41
     */
    public static Boolean checkPasswordHash(String passwordToCheck, String passwordHashed) {
        if (passwordToCheck == null) {
            return false;
        }
        String hashed = generatePasswordHash(passwordToCheck);
        return hashed.equals(passwordHashed);
    }
}
