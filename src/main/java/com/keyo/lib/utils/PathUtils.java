package com.keyo.lib.utils;

import java.io.File;

/**
 * ·��������
 *
 * @Author Keyo
 * @date 2024/7/20
 */
public class PathUtils {

    /**
     * ��ȡ��Ŀ·��
     *
     * @return
     */
    public static String getProjectPath() {
        File file = new File("");
        return file.getAbsolutePath();
    }

    /**
     * ƴ��·��
     *
     * @param parentPath
     * @param name
     * @return
     */
    public static String concatPath(String parentPath, String name) {
        return parentPath + File.separator + name;
    }
}
