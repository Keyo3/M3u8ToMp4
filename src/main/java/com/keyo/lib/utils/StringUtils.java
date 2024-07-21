package com.keyo.lib.utils;

/**
 * @Author Keyo
 * @date 2024/7/20
 */
public class StringUtils {

    /**
     * Ϊ��
     *
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * ��Ϊ��
     *
     * @param str
     * @return
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * Ĭ���ַ���
     *
     * @param str
     * @return
     */
    public static String defaultString(String str) {
        return isBlank(str) ? "" : str;
    }
}
