package com.keyo.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * M3u8
 *
 * @Author Keyo
 * @date 2024/7/21
 */
@Data
public class M3u8 {

    /**
     * �ļ���
     */
    private String fileName = "";
    /**
     * �Ƿ����
     */
    private boolean haveDecrypt = false;
    /**
     * ������
     */
    private Decryptor decryptor;
    /**
     * ��Ƭ�ļ�����·��
     */
    private List<String> sectionAbsolutePathList = new ArrayList<>();
}
