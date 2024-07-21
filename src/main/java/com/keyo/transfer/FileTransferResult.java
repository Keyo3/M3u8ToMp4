package com.keyo.transfer;

import lombok.Data;

/**
 * 转换结果
 *
 * @Author Keyo
 * @date 2024/7/20
 */
@Data
public class FileTransferResult {

    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 文件名
     */
    private String fileName;

    private FileTransferResult(boolean success) {
        this.success = success;
    }

    /**
     * 成功
     *
     * @param fileName 文件名
     * @return
     */
    public static FileTransferResult success(String fileName) {
        FileTransferResult fileTransferResult = new FileTransferResult(true);
        fileTransferResult.setFileName(fileName);
        return fileTransferResult;
    }

    /**
     * 失败
     *
     * @return 文件名
     */
    public static FileTransferResult fail(String fileName) {
        FileTransferResult fileTransferResult = new FileTransferResult(false);
        fileTransferResult.setFileName(fileName);
        return fileTransferResult;
    }

    @Override
    public String toString() {
        return "FileTransferResult{" +
                "success=" + success +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}