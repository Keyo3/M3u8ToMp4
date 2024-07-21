package com.keyo.builder.decryptor;

import com.keyo.bean.Decryptor;
import com.keyo.constant.DecryptConstant;
import com.keyo.enums.DecryptorEnum;

/**
 * AES算法解密器建造者
 *
 * @Author Keyo
 * @date 2024/7/21
 */
public class AESDecryptorBuilder implements DecryptorBuilder {

    @Override
    public Decryptor build() {
        Decryptor decryptor = new Decryptor();
        decryptor.setMethod(DecryptorEnum.AES.getId());
        decryptor.setInitParameter(DecryptConstant.IV);
        decryptor.setModel(DecryptConstant.AES_MODEL);
        return decryptor;
    }
}