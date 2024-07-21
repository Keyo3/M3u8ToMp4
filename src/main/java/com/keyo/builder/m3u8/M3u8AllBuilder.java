package com.keyo.builder.m3u8;

import com.keyo.bean.Decryptor;
import com.keyo.bean.M3u8;
import com.keyo.bean.M3u8FileAndContent;
import com.keyo.builder.decryptor.DecryptorBuilder;
import com.keyo.enums.DecryptorEnum;
import com.keyo.factory.DecryptorFactory;
import com.keyo.lib.utils.FileUtils;
import com.keyo.lib.utils.PathUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Keyo
 * @date 2024/7/21
 */
public class M3u8AllBuilder implements M3u8Builder {

    /**
     * M3u8文件和内容
     */
    private M3u8FileAndContent m3u8FileAndContent;

    public M3u8AllBuilder(M3u8FileAndContent m3u8FileAndContent) {
        this.m3u8FileAndContent = m3u8FileAndContent;
    }

    @Override
    public M3u8 build() {
        File m3u8FileFile = m3u8FileAndContent.getM3u8FileFile();
        File contentDirectoryFile = m3u8FileAndContent.getContentDirectoryFile();

        String m3u8FileContent = FileUtils.readFile(m3u8FileFile.getAbsolutePath());
        String[] split = m3u8FileContent.split("\n");
        String contentDirectoryName = contentDirectoryFile.getName();
        String contentDirectoryAbsolutePath = contentDirectoryFile.getAbsolutePath();

        List<String> sectionAbsolutePathList = new ArrayList<>();
        String decryptMethod = "";
        String absolutePathOfKey = "";
        boolean haveDecrypt = false;
        for (int i = 0; i < split.length; i++) {
            String line = split[i];
            if (line.contains("#EXTINF")) {
                // 在设备中真实的路径
                String trueContentPath = split[i + 1];
                int index = trueContentPath.lastIndexOf(contentDirectoryName);
                String sectionRelativePath = trueContentPath.substring(index + contentDirectoryName.length() + 1);
                String sectionAbsolutePath = PathUtils.concatPath(contentDirectoryAbsolutePath, sectionRelativePath);
                sectionAbsolutePathList.add(sectionAbsolutePath);
            } else if (line.contains("#EXT-X-KEY")) {
                haveDecrypt = true;
                int startIndex = line.indexOf("METHOD=");
                if (startIndex > -1) {
                    startIndex += "METHOD=".length();
                    int endIndex = line.indexOf(",", startIndex);
                    decryptMethod = line.substring(startIndex, endIndex);
                }


                startIndex = line.indexOf("URI=\"");
                if (startIndex > -1) {
                    startIndex += "URI=\"".length();
                    int endIndex = line.indexOf("\"", startIndex);
                    absolutePathOfKey = line.substring(startIndex, endIndex);
                    int index = absolutePathOfKey.lastIndexOf(contentDirectoryName);
                    String relativePathOfKey = absolutePathOfKey.substring(index + contentDirectoryName.length() + 1);
                    absolutePathOfKey = PathUtils.concatPath(contentDirectoryAbsolutePath, relativePathOfKey);
                }
            }
        }

        M3u8 m3u8 = new M3u8();
        m3u8.setFileName(m3u8FileAndContent.getFileName());
        String key = FileUtils.readFile(absolutePathOfKey);

        Decryptor decryptor = null;
        if (decryptMethod.contains(DecryptorEnum.AES.getId())) {
            DecryptorBuilder builder = DecryptorFactory.getBuilder(DecryptorEnum.AES);
            decryptor = builder.build();
            decryptor.setKey(key);
        } else {
            DecryptorBuilder builder = DecryptorFactory.getBuilder(DecryptorEnum.AES);
            decryptor = builder.build();
        }

        m3u8.setDecryptor(decryptor);
        m3u8.setHaveDecrypt(haveDecrypt);
        m3u8.setSectionAbsolutePathList(sectionAbsolutePathList);
        return m3u8;
    }
}
