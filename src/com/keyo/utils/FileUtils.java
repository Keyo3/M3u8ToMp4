package com.keyo.utils;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * 文件工具类
 *
 * @Author Keyo
 * @date 2024/5/3
 */
public class FileUtils {

    public static byte[] readFileBytes(String filePath) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(filePath);
            int available = fileInputStream.available();
            byte[] bytes = new byte[available];
            fileInputStream.read(bytes);
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseableUtils.close(fileInputStream);
        }
        return null;
    }

    public static String readFile(String filePath) {
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(filePath);
            int available = fileInputStream.available();
            byte[] bytes = new byte[available];
            fileInputStream.read(bytes);
            return new String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseableUtils.close(fileInputStream);
        }
        return "";
    }
}
