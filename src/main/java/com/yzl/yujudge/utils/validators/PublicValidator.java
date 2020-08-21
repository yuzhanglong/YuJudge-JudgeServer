package com.yzl.yujudge.utils.validators;

/**
 * 公共验证器，各种杂项验证
 *
 * @author yuzhanglong
 * @date 2020-8-21 22:40:29
 */
public class PublicValidator {
    public static final String IS_ADDRESS_REGEX = "(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]";

    /**
     * 判断IP合法性
     *
     * @author yuzhanglong
     * @date 2020-8-21 22:41:47
     */
    public static Boolean isAddressValidated(String raw) {
        if (raw != null && !raw.isEmpty()) {
            return raw.matches(IS_ADDRESS_REGEX);
        }
        return false;
    }

    /**
     * 判断端口合法性
     *
     * @author yuzhanglong
     * @date 2020-8-21 22:46:41
     */
    public static Boolean isPortValidated(Integer port) {
        return port > 0 && port < 65536;
    }
}
