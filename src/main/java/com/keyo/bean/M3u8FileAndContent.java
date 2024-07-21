package com.keyo.bean;

import lombok.Data;

import java.io.File;

/**
 * M3u8�ļ�������
 *
 * @Author Keyo
 * @date 2024/7/21
 */
@Data
public class M3u8FileAndContent {
    /**
     * �ļ���
     */
    private String fileName = "";
    /**
     * m3u8�ļ�
     */
    private File m3u8FileFile;
    /**
     * �����ļ���
     */
    private File contentDirectoryFile;

    public M3u8FileAndContent(String fileName) {
        this.fileName = fileName;
    }
}