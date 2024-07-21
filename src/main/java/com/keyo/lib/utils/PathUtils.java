package com.keyo.lib.utils;

import java.io.File;

/**
 * 路径工具类
 *
 * @Author Keyo
 * @date 2024/7/20
 */
public class PathUtils {

    /**
     * 获取项目路径
     *
     * @return
     */
    public static String getProjectPath() {
        File file = new File("");
        return file.getAbsolutePath();
    }

    /**
     * 拼接路径
     *
     * @param parentPath
     * @param name
     * @return
     */
    public static String concatPath(String parentPath, String name) {
        return parentPath + File.separator + name;
    }
}
