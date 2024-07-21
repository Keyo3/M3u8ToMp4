package com.keyo.lib.utils;

/**
 * @Author Keyo
 * @date 2024/7/20
 */
public class StringUtils {

    /**
     * 为空
     *
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * 不为空
     *
     * @param str
     * @return
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 默认字符串
     *
     * @param str
     * @return
     */
    public static String defaultString(String str) {
        return isBlank(str) ? "" : str;
    }
}
