package com.keyo.transfer;

import com.keyo.bean.Decryptor;
import com.keyo.bean.M3u8;
import com.keyo.bean.M3u8FileAndContent;
import com.keyo.builder.m3u8.M3u8Builder;
import com.keyo.builder.m3u8.M3u8FileAndContentBuilder;
import com.keyo.factory.M3u8Factory;
import com.keyo.lib.utils.CloseableUtils;
import com.keyo.lib.utils.FileUtils;
import com.keyo.lib.utils.ListUtils;
import com.keyo.lib.utils.PathUtils;
import com.keyo.log.Logger;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * M3u8转换为Mp4
 *
 * @Author Keyo
 * @date 2024/7/20
 */
public class M3U8FileTransferToMp4 extends FileTransfer {

    private Logger logger = Logger.logger;

    public M3U8FileTransferToMp4() {
        String projectPath = PathUtils.getProjectPath();
        inputDirectoryAbsolutePath = PathUtils.concatPath(projectPath, "input");
        outputDirectoryAbsolutePath = PathUtils.concatPath(projectPath, "output");

        if (!FileUtils.createAndCheckDirectory(inputDirectoryAbsolutePath)) {
            System.exit(0);
        }

        if (!FileUtils.createAndCheckDirectory(outputDirectoryAbsolutePath)) {
            System.exit(0);
        }
    }

    @Override
    public List<FileTransferResult> fileTransfer() {
        // 从输入文件夹获取m3u8
        List<M3u8> m3u8List = analyseM3u8List();

        // 将m3u9转换到mp4并放在输出文件夹
        return transfer(m3u8List);
    }

    /**
     * 解析M3u8
     *
     * @return
     */
    private List<M3u8> analyseM3u8List() {
        // 读取m3u8
        logger.log("开始读取----------------");
        M3u8FileAndContentBuilder m3u8FileAndContentBuilder = new M3u8FileAndContentBuilder(inputDirectoryAbsolutePath);
        List<M3u8FileAndContent> m3u8FileAndContentList = m3u8FileAndContentBuilder.build();

        // 建造m3u8
        List<M3u8> m3u8List = new ArrayList<>();
        for (M3u8FileAndContent m3u8FileAndContent : m3u8FileAndContentList) {
            M3u8Builder builder = M3u8Factory.getBuilder(m3u8FileAndContent);
            m3u8List.add(builder.build());
        }
        logger.log("读取完成----------------");
        return m3u8List;
    }

    /**
     * 转换
     *
     * @param m3u8List
     * @return
     */
    public List<FileTransferResult> transfer(List<M3u8> m3u8List) {
        logger.log("转换开始----------------");
        List<FileTransferResult> fileTransferResultList = new ArrayList<>();

        for (M3u8 m3u8 : m3u8List) {
            FileTransferResult fileTransferResult = transfer(m3u8);
            fileTransferResultList.add(fileTransferResult);
        }

        logger.log("转换完成----------------");
        return fileTransferResultList;
    }

    /**
     * 转换
     *
     * @param m3u8
     * @return
     */
    public FileTransferResult transfer(M3u8 m3u8) {
        String fileName = m3u8.getFileName();
        FileOutputStream fileOutputStream = null;
        try {
            logger.log("开始转换：" + fileName + "----------------");
            String outputFileAbsolutPath = PathUtils.concatPath(outputDirectoryAbsolutePath, fileName + ".mp4");
            List<String> sectionAbsolutePathList = m3u8.getSectionAbsolutePathList();
            if (ListUtils.isBlank(sectionAbsolutePathList)) {
                logger.log("未找到视频文件");
                logger.log("转换结束");
                return FileTransferResult.fail(fileName);
            }

            boolean haveDecrypt = m3u8.isHaveDecrypt();
            Decryptor decryptor = m3u8.getDecryptor();

            fileOutputStream = new FileOutputStream(outputFileAbsolutPath, true);
            for (int i = 0; i < sectionAbsolutePathList.size(); i++) {
                String sectionAbsolutePath = sectionAbsolutePathList.get(i);
                logger.log("转换中：" + i + "/" + sectionAbsolutePathList.size() + "\t" + sectionAbsolutePath);
                // 切片文件
                byte bytes[] = FileUtils.readFileBytes(sectionAbsolutePath);
                if (haveDecrypt) {
                    bytes = decryptor.decrypt(bytes);
                }
                fileOutputStream.write(bytes);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(fileName + "转换结束");
            return FileTransferResult.fail(fileName);
        } finally {
            CloseableUtils.close(fileOutputStream);
        }
        logger.log(fileName + "转换结束");
        return FileTransferResult.success(fileName);
    }
}