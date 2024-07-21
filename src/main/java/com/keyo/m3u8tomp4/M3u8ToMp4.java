package com.keyo.m3u8tomp4;

import com.keyo.lib.utils.CloseableUtils;
import com.keyo.lib.utils.FileUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * M3u8转mp4
 *
 * @author Keyo
 * @date 2024/5/3
 */
public class M3u8ToMp4 {

    /**
     * AES算法
     */
    private final static String AES = "AES";
    /**
     * IV初始值
     */
    private static final String IV = "0000000000000000";
    /**
     * 模式
     */
    private static final String MODEL = "AES/CBC/PKCS5Padding";
    /**
     * 输入的文件夹
     */
    private String inputPath = "";
    /**
     * 输出的文件夹
     */
    private String outputPath = "";

    public M3u8ToMp4() throws Exception {
        File file = new File("");
        String userPath = file.getAbsolutePath();

        System.out.println("userPath:" + userPath);
        inputPath = userPath + "\\" + "input";
        outputPath = userPath + "\\" + "output";

        File inputFile = new File(inputPath);
        File outputFile = new File(outputPath);

        if (!inputFile.exists() || !outputFile.exists()) {
            throw new FileNotFoundException("文件夹不存在");
        }
        if (!inputFile.isDirectory() || !outputFile.isDirectory()) {
            throw new Exception("路径非文件夹路径");
        }
    }

    /**
     * 转换
     */
    public void transfer() {
        File directory = new File(inputPath);
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                transfer(file.getName(), inputPath + "\\" + file.getName());
            }
        }
    }

    /**
     * 转换
     *
     * @param m3u8FileName
     * @param contentPath
     */
    private void transfer(String m3u8FileName, String contentPath) {
        System.out.println("正在转换：----------------------------" + m3u8FileName);

        int max = getMaxNumOfSplit(contentPath);
        String key = getKey(contentPath);
        System.out.println("当前分片数量：" + max);
        System.out.println("当前密钥：" + key);

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(outputPath + "\\" + m3u8FileName + ".mp4", true);
            writeMp4Byte(fileOutputStream, contentPath, max, key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseableUtils.close(fileOutputStream);
        }
        System.out.println(m3u8FileName + "转换结束----------------------------");
    }

    /**
     * 是否有密钥
     *
     * @param m3u8ContentPath
     * @return
     */
    private String getKey(String m3u8ContentPath) {
        File file = new File(m3u8ContentPath);
        String[] fileNames = file.list();
        for (String fileName : fileNames) {
            if (fileName.endsWith(".key")) {
                String key = FileUtils.readFile(m3u8ContentPath + "\\" + fileName);
                return key;
            }
        }
        return "";
    }

    /**
     * 获取mp4的内容
     *
     * @param m3u8ContentPath
     * @param max
     * @return
     */
    private void writeMp4Byte(FileOutputStream fileOutputStream, String m3u8ContentPath, int max, String key) {
        System.out.println("开始生成mp4文件");
        try {
            for (int i = 0; i < max; i++) {
                String splitPath = m3u8ContentPath + "\\" + i;
                File file = new File(splitPath);
                if (!file.exists()) {
                    continue;
                }
                System.out.println("解析：" + splitPath);
                byte bytes[] = FileUtils.readFileBytes(splitPath);
                if (key != null && !key.isEmpty()) {
                    bytes = decrypt(bytes, key);
                }
                fileOutputStream.write(bytes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseableUtils.close(fileOutputStream);
        }
        System.out.println("mp4文件生成结束");
    }

    /**
     * 解密
     *
     * @param bytes
     * @param key
     * @return
     * @throws Exception
     */
    private byte[] decrypt(byte[] bytes, String key) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), AES);
        IvParameterSpec ivSpec = new IvParameterSpec(IV.getBytes());
        Cipher cipher = Cipher.getInstance(MODEL);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec);
        byte[] result = cipher.doFinal(bytes);
        return result;
    }

    /**
     * 获取分片的最大值
     *
     * @param m3u8ContentPath
     * @return
     */
    private static int getMaxNumOfSplit(String m3u8ContentPath) {
        File file = new File(m3u8ContentPath);
        String[] fileNames = file.list();

        int max = 0;
        for (String fileName : fileNames) {
            try {
                int i = Integer.parseInt(fileName);
                if (i > max) {
                    max = i;
                }
            } catch (Exception e) {
                System.out.println("Integer.parseInt Exception:" + fileName);
            }
        }
        return max;
    }
}