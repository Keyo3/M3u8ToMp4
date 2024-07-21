package com.keyo.transfer;

import java.util.List;

/**
 * �ļ�ת����
 *
 * @Author Keyo
 * @date 2024/7/20
 */
public abstract class FileTransfer {

    /**
     * ������ļ���
     */
    protected String inputDirectoryAbsolutePath = "";
    /**
     * ������ļ���
     */
    protected String outputDirectoryAbsolutePath = "";

    /**
     * ת��
     *
     * @return
     */
    public abstract List<FileTransferResult> fileTransfer();
}
