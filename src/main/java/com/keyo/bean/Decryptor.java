package com.keyo.bean;

import lombok.Data;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 解密者
 *
 * @Author Keyo
 * @date 2024/7/20
 */
@Data
public class Decryptor {

    /**
     * 密钥
     */
    private String key = "";
    /**
     * 加密算法
     */
    private String method = "";
    /**
     * 初始值
     */
    private String initParameter = "";
    /**
     * 模式
     */
    private String model = "";

    /**
     * 解密
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