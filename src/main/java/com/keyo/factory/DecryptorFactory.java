package com.keyo.factory;

import com.keyo.builder.decryptor.AESDecryptorBuilder;
import com.keyo.builder.decryptor.DecryptorBuilder;
import com.keyo.enums.DecryptorEnum;

/**
 * 解密器工厂类
 *
 * @Author Keyo
 * @date 2024/7/21
 */
public interface DecryptorFactory {

    public static DecryptorBuilder getBuilder(DecryptorEnum decryptorEnum) {
        if (DecryptorEnum.AES == decryptorEnum) {
            return new AESDecryptorBuilder();
        } else {
            // 默认AES算法解密器
            return new AESDecryptorBuilder();
        }
    }
}
