package com.keyo.transfer;

import java.util.List;

/**
 * 文件转换器
 *
 * @Author Keyo
 * @date 2024/7/20
 */
public abstract class FileTransfer {

    /**
     * 输入的文件夹
     */
    protected String inputDirectoryAbsolutePath = "";
    /**
     * 输出的文件夹
     */
    protected String outputDirectoryAbsolutePath = "";

    /**
     * 转换
     *
     * @return
     */
    public abstract List<FileTransferResult> fileTransfer();
}
