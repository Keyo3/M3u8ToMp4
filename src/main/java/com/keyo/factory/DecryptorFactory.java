package com.keyo.factory;

import com.keyo.builder.decryptor.AESDecryptorBuilder;
import com.keyo.builder.decryptor.DecryptorBuilder;
import com.keyo.enums.DecryptorEnum;

/**
 * ������������
 *
 * @Author Keyo
 * @date 2024/7/21
 */
public interface DecryptorFactory {

    public static DecryptorBuilder getBuilder(DecryptorEnum decryptorEnum) {
        if (DecryptorEnum.AES == decryptorEnum) {
            return new AESDecryptorBuilder();
        } else {
            // Ĭ��AES�㷨������
            return new AESDecryptorBuilder();
        }
    }
}
