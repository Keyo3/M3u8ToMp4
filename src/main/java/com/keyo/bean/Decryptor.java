package com.keyo.bean;

import lombok.Data;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * ������
 *
 * @Author Keyo
 * @date 2024/7/20
 */
@Data
public class Decryptor {

    /**
     * ��Կ
     */
    private String key = "";
    /**
     * �����㷨
     */
    private String method = "";
    /**
     * ��ʼֵ
     */
    private String initParameter = "";
    /**
     * ģʽ
     */
    private String model = "";

    /**
     * ����
     *
     * @param bytes
     * @return
     * @throws Exception
     */
    public byte[] decrypt(byte[] bytes) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), method);
        IvParameterSpec ivSpec = new IvParameterSpec(initParameter.getBytes());
        Cipher cipher = Cipher.getInstance(model);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec);
        byte[] result = cipher.doFinal(bytes);
        return result;
    }
}