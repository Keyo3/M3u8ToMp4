package com.keyo.builder.m3u8;

import com.keyo.bean.Decryptor;
import com.keyo.bean.M3u8;
import com.keyo.bean.M3u8FileAndContent;
import com.keyo.builder.decryptor.DecryptorBuilder;
import com.keyo.enums.DecryptorEnum;
import com.keyo.factory.DecryptorFactory;
import com.keyo.lib.utils.FileUtils;

import java.io.File;

/**
 * @Author Keyo
 * @date 2024/7/21
 */
public class M3u8FileBuilder implements M3u8Builder {

    /**
     * M3u8文件和内容
     */
    private M3u8FileAndContent m3u8FileAndContent;

    public M3u8FileBuilder(M3u8FileAndContent m3u8FileAndContent) {
        this.m3u8FileAndContent = m3u8FileAndContent;
    }

    @Override
    public M3u8 build() {
        File m3u8FileFile = m3u8FileAndContent.getM3u8FileFile();
        String m3u8FileContent = FileUtils.readFile(m3u8FileFile.getAbsolutePath());
        String[] split = m3u8FileContent.split("\n");

        String decryptMethod = "";
        boolean haveDecrypt = false;
        for (int i = 0; i < split.length; i++) {
            String line = split[i];
            if (line.contains("#EXT-X-KEY")) {
                haveDecrypt = true;
                int startIndex = line.indexOf("METHOD=");
                if (startIndex > -1) {
                    startIndex += "METHOD=".length();
                    int endIndex = line.indexOf(",", startIndex);
                    decryptMethod = line.substring(startIndex, endIndex);
                }
            }
        }

        M3u8 m3u8 = new M3u8();
        m3u8.setFileName(m3u8FileAndContent.getFileName());
        Decryptor decryptor = null;
        if (decryptMethod.contains(DecryptorEnum.AES.getId())) {
            DecryptorBuilder builder = DecryptorFactory.getBuilder(DecryptorEnum.AES);
            decryptor = builder.build();
        } else {
            DecryptorBuilder builder = DecryptorFactory.getBuilder(DecryptorEnum.AES);
            decryptor = builder.build();
        }
        m3u8.setDecryptor(decryptor);
        m3u8.setHaveDecrypt(haveDecrypt);
        return m3u8;
    }
}