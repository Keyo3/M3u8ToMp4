package com.keyo.builder.m3u8;

import com.keyo.bean.M3u8FileAndContent;
import com.keyo.builder.Builder;
import com.keyo.lib.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Keyo
 * @date 2024/7/21
 */
public class M3u8FileAndContentBuilder implements Builder<List<M3u8FileAndContent>> {

    /**
     * 绝对路径
     */
    private String absolutePath;

    public M3u8FileAndContentBuilder(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    @Override
    public List<M3u8FileAndContent> build() {
        Map<String, M3u8FileAndContent> m3u8FileAndContentMap = new HashMap<>();
        File directory = new File(absolutePath);
        File[] files = directory.listFiles();
        for (File file : files) {
            String fileName = file.getName();
            boolean isM3u8File = FileUtils.isM3u8File(fileName);

            // m3u8文件就去掉最后的文件名后缀
            String fileNameAsKey = isM3u8File ? FileUtils.removeFileSuffix(fileName) : fileName.replace(".m3u8_contents", "");
            if (file.isDirectory()) {
                if (m3u8FileAndContentMap.containsKey(fileNameAsKey)) {
                    M3u8FileAndContent m3U8FileAndContent = m3u8FileAndContentMap.get(fileNameAsKey);
                    m3U8FileAndContent.setContentDirectoryFile(file);
                } else {
                    M3u8FileAndContent m3U8FileAndContent = new M3u8FileAndContent(fileNameAsKey);
                    m3U8FileAndContent.setContentDirectoryFile(file);
                    m3u8FileAndContentMap.put(fileNameAsKey, m3U8FileAndContent);
                }
            } else if (isM3u8File) {
                if (m3u8FileAndContentMap.containsKey(fileNameAsKey)) {
                    M3u8FileAndContent m3U8FileAndContent = m3u8FileAndContentMap.get(fileNameAsKey);
                    m3U8FileAndContent.setM3u8FileFile(file);
                } else {
                    M3u8FileAndContent m3U8FileAndContent = new M3u8FileAndContent(fileNameAsKey);
                    m3U8FileAndContent.setM3u8FileFile(file);
                    m3u8FileAndContentMap.put(fileNameAsKey, m3U8FileAndContent);
                }
            }
        }

        List<M3u8FileAndContent> m3U8FileAndContentList = new ArrayList<>();
        for (Map.Entry<String, M3u8FileAndContent> entry : m3u8FileAndContentMap.entrySet()) {
            m3U8FileAndContentList.add(entry.getValue());
        }

        return m3U8FileAndContentList;
    }
}