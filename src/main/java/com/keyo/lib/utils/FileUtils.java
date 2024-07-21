package com.keyo.lib.utils;

import com.keyo.constant.FileTypeConstant;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 文件工具类
 *
 * @Author Keyo
 * @date 2024/5/3
 */
public class FileUtils {

    /**
     * 读取文件字节流
     *
     * @param fileAbsolutePath
     * @return
     */
    public static byte[] readFileBytes(String fileAbsolutePath) {
        if (StringUtils.isBlank(fileAbsolutePath)) {
            return new byte[0];
        }

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(fileAbsolutePath);
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

    /**
     * 读取文件字符流
     *
     * @param fileAbsolutePath
     * @return
     */
    public static String readFile(String fileAbsolutePath) {
        byte[] bytes = readFileBytes(fileAbsolutePath);
        if (bytes == null || bytes.length == 0) {
            return "";
        }

        return new String(bytes);
    }

    /**
     * 是否M3u8文件
     *
     * @param fileName
     * @return
     */
    public static boolean isM3u8File(String fileName) {
        return isFileType(fileName, FileTypeConstant.M3U8);
    }

    /**
     * 是否某种类型的文件
     *
     * @param fileName
     * @param fileType
     * @return
     */
    public static boolean isFileType(String fileName, String fileType) {
        if (StringUtils.isBlank(fileName)) {
            return false;
        }

        return fileName.endsWith(fileType);
    }

    /**
     * 去除文件后缀名
     *
     * @param fileName
     * @return
     */
    public static String removeFileSuffix(String fileName) {
        if (StringUtils.isBlank(fileName)) {
            return "";
        }

        int index = fileName.lastIndexOf(".");
        return fileName.substring(0, index);
    }

    /**
     * 创建并检查文件夹
     *
     * @param absolutePath
     * @return
     */
    public static boolean createAndCheckDirectory(String absolutePath) {
        File file = new File(absolutePath);
        if (!file.exists()) {
            if (file.mkdirs()) {
                System.out.println(absolutePath + "文件夹创建失败");
                return false;
            }
        }

        if (!file.isDirectory()) {
            System.out.println(absolutePath + "为非文件夹");
            return false;
        }

        return true;
    }

    /**
     * 创建并检查文件夹
     *
     * @param absolutePath
     * @return
     */
    public static boolean createAndCheckFile(String absolutePath) throws IOException {
        File file = new File(absolutePath);
        if (!file.exists()) {
            if (!file.createNewFile()) {
                System.out.println(absolutePath + "文件创建失败");
                return false;
            }
        }

        if (!file.isFile()) {
            System.out.println(absolutePath + "为非文件");
            return false;
        }

        return true;
    }
}