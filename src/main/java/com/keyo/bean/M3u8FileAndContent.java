package com.keyo.bean;

import lombok.Data;

import java.io.File;

/**
 * M3u8文件和内容
 *
 * @Author Keyo
 * @date 2024/7/21
 */
@Data
public class M3u8FileAndContent {
    /**
     * 文件名
     */
    private String fileName = "";
    /**
     * m3u8文件
     */
    private File m3u8FileFile;
    /**
     * 内容文件夹
     */
    private File contentDirectoryFile;

    public M3u8FileAndContent(String fileName) {
        this.fileName = fileName;
    }
}