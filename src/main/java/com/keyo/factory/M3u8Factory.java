package com.keyo.factory;

import com.keyo.bean.M3u8FileAndContent;
import com.keyo.builder.m3u8.M3u8AllBuilder;
import com.keyo.builder.m3u8.M3u8Builder;
import com.keyo.builder.m3u8.M3u8ContentBuilder;
import com.keyo.builder.m3u8.M3u8FileBuilder;

import java.io.File;

/**
 * @Author Keyo
 * @date 2024/7/21
 */
public class M3u8Factory {

    public static M3u8Builder getBuilder(M3u8FileAndContent m3u8FileAndContent) {
        File m3u8FileFile = m3u8FileAndContent.getM3u8FileFile();
        File contentDirectoryFile = m3u8FileAndContent.getContentDirectoryFile();

        if (m3u8FileFile == null && contentDirectoryFile == null) {
            return null;
        }

        if (m3u8FileFile != null && contentDirectoryFile != null) {
            return new M3u8AllBuilder(m3u8FileAndContent);
        } else if (m3u8FileFile == null) {
            return new M3u8ContentBuilder(m3u8FileAndContent);
        } else if (contentDirectoryFile == null) {
            return new M3u8FileBuilder(m3u8FileAndContent);
        } else {
            return null;
        }
    }

}
