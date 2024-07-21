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
 * M3u8תmp4
 *
 * @author Keyo
 * @date 2024/5/3
 */
public class M3u8ToMp4 {

    /**
     * AES�㷨
     */
    private final static String AES = "AES";
    /**
     * IV��ʼֵ
     */
    private static final String IV = "0000000000000000";
    /**
     * ģʽ
     */
    private static final String MODEL = "AES/CBC/PKCS5Padding";
    /**
     * ������ļ���
     */
    private String inputPath = "";
    /**
     * ������ļ���
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
            throw new FileNotFoundException("�ļ��в�����");
        }
        if (!inputFile.isDirectory() || !outputFile.isDirectory()) {
            throw new Exception("·�����ļ���·��");
        }
    }

    /**
     * ת��
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
     * ת��
     *
     * @param m3u8FileName
     * @param contentPath
     */
    private void transfer(String m3u8FileName, String contentPath) {
        System.out.println("����ת����----------------------------" + m3u8FileName);

        int max = getMaxNumOfSplit(contentPath);
        String key = getKey(contentPath);
        System.out.println("��ǰ��Ƭ������" + max);
        System.out.println("��ǰ��Կ��" + key);

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(outputPath + "\\" + m3u8FileName + ".mp4", true);
            writeMp4Byte(fileOutputStream, contentPath, max, key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseableUtils.close(fileOutputStream);
        }
        System.out.println(m3u8FileName + "ת������----------------------------");
    }

    /**
     * �Ƿ�����Կ
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
     * ��ȡmp4������
     *
     * @param m3u8ContentPath
     * @param max
     * @return
     */
    private void writeMp4Byte(FileOutputStream fileOutputStream, String m3u8ContentPath, int max, String key) {
        System.out.println("��ʼ����mp4�ļ�");
        try {
            for (int i = 0; i < max; i++) {
                String splitPath = m3u8ContentPath + "\\" + i;
                File file = new File(splitPath);
                if (!file.exists()) {
                    continue;
                }
                System.out.println("������" + splitPath);
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
        System.out.println("mp4�ļ����ɽ���");
    }

    /**
     * ����
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
     * ��ȡ��Ƭ�����ֵ
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