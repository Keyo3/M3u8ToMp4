package com.keyo.lib.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 列表工具类
 *
 * @Author Keyo
 * @date 2024/7/21
 */
public class ListUtils {

    /**
     * 为空
     *
     * @param list
     * @return
     */
    public static boolean isBlank(List list) {
        return list == null || list.isEmpty();
    }

    /**
     * 如果为空，返回默认list
     *
     * @param list
     * @return
     */
    public static List defaultList(List list) {
        return isBlank(list) ? Collections.emptyList() : list;
    }

    /**
     * 获取Map
     *
     * @param list
     * @param index
     * @return
     */
    public static <T> T getObject(List<T> list, int index) {
        try {
            return list.get(index);
        } catch (Exception e) {
             return null;
        }
    }

    /**
     * 获取数字列表
     *
     * @param prefix
     * @param num
     * @return
     */
    public static List getNumberStringList(String prefix, int num) {
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(prefix + i);
        }
        return list;
    }

    public static String transferString(List<String> list, String split) {
        if (isBlank(list)) {
            return "";
        }

        String str = "";
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            if (i == list.size() - 1) {
                str += s;
            } else {
                str += s + split;
            }
        }

        return str;
    }
}
