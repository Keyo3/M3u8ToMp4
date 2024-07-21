package com.keyo.builder.m3u8;

import com.keyo.bean.Decryptor;
import com.keyo.bean.M3u8;
import com.keyo.bean.M3u8FileAndContent;
import com.keyo.builder.decryptor.DecryptorBuilder;
import com.keyo.constant.FileTypeConstant;
import com.keyo.enums.DecryptorEnum;
import com.keyo.factory.DecryptorFactory;
import com.keyo.lib.utils.FileUtils;
import com.keyo.lib.utils.ListUtils;

import java.io.File;

/**
 * @Author Keyo
 * @date 2024/7/21
 */
public class M3u8ContentBuilder implements M3u8Builder {

    /**
     * M3u8文件和内容
     */
    private M3u8FileAndContent m3u8FileAndContent;

    public M3u8ContentBuilder(M3u8FileAndContent m3u8FileAndContent) {
        this.m3u8FileAndContent = m3u8FileAndContent;
    }

    @Override
    public M3u8 build() {
        String key = "";
        int maxOfSection = 0;
        boolean haveDecrypt = false;
        File contentDirectoryFile = m3u8FileAndContent.getContentDirectoryFile();
        File[] files = contentDirectoryFile.listFiles();
        for (File file : files) {
            String fileName = file.getName();
            if (FileUtils.isFileType(fileName, FileTypeConstant.KEY)) {
                haveDecrypt = true;
                key = FileUtils.readFile(file.getAbsolutePath());
            } else {
                try {
                    int num = Integer.parseInt(fileName);
                    maxOfSection = Math.max(maxOfSection, num);
                } catch (Exception e) {
                    System.out.println("不符合命名的文件：" + file.getAbsolutePath());
                }
            }
        }

        DecryptorBuilder builder = DecryptorFactory.getBuilder(DecryptorEnum.AES);
        Decryptor decryptor = builder.build();
        decryptor.setKey(key);

        M3u8 m3u8 = new M3u8();
        m3u8.setFileName(m3u8FileAndContent.getFileName());
        m3u8.setHaveDecrypt(haveDecrypt);
        m3u8.setDecryptor(decryptor);
        m3u8.setSectionAbsolutePathList(ListUtils.getNumberStringList(contentDirectoryFile.getAbsolutePath() + File.separator, maxOfSection));
        return m3u8;
    }
}
