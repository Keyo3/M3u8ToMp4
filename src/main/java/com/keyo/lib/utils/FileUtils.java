package com.keyo.lib.utils;

import com.keyo.constant.FileTypeConstant;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * �ļ�������
 *
 * @Author Keyo
 * @date 2024/5/3
 */
public class FileUtils {

    /**
     * ��ȡ�ļ��ֽ���
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
     * ��ȡ�ļ��ַ���
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
     * �Ƿ�M3u8�ļ�
     *
     * @param fileName
     * @return
     */
    public static boolean isM3u8File(String fileName) {
        return isFileType(fileName, FileTypeConstant.M3U8);
    }

    /**
     * �Ƿ�ĳ�����͵��ļ�
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
     * ȥ���ļ���׺��
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
     * ����������ļ���
     *
     * @param absolutePath
     * @return
     */
    public static boolean createAndCheckDirectory(String absolutePath) {
        File file = new File(absolutePath);
        if (!file.exists()) {
            if (file.mkdirs()) {
                System.out.println(absolutePath + "�ļ��д���ʧ��");
                return false;
            }
        }

        if (!file.isDirectory()) {
            System.out.println(absolutePath + "Ϊ���ļ���");
            return false;
        }

        return true;
    }

    /**
     * ����������ļ���
     *
     * @param absolutePath
     * @return
     */
    public static boolean createAndCheckFile(String absolutePath) throws IOException {
        File file = new File(absolutePath);
        if (!file.exists()) {
            if (!file.createNewFile()) {
                System.out.println(absolutePath + "�ļ�����ʧ��");
                return false;
            }
        }

        if (!file.isFile()) {
            System.out.println(absolutePath + "Ϊ���ļ�");
            return false;
        }

        return true;
    }
}