package com.keyo.transfer;

import lombok.Data;

/**
 * ת�����
 *
 * @Author Keyo
 * @date 2024/7/20
 */
@Data
public class FileTransferResult {

    /**
     * �Ƿ�ɹ�
     */
    private boolean success;
    /**
     * �ļ���
     */
    private String fileName;

    private FileTransferResult(boolean success) {
        this.success = success;
    }

    /**
     * �ɹ�
     *
     * @param fileName �ļ���
     * @return
     */
    public static FileTransferResult success(String fileName) {
        FileTransferResult fileTransferResult = new FileTransferResult(true);
        fileTransferResult.setFileName(fileName);
        return fileTransferResult;
    }

    /**
     * ʧ��
     *
     * @return �ļ���
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