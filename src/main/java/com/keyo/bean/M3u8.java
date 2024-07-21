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
     * 文件名
     */
    private String fileName = "";
    /**
     * 是否加密
     */
    private boolean haveDecrypt = false;
    /**
     * 解密者
     */
    private Decryptor decryptor;
    /**
     * 切片文件绝对路径
     */
    private List<String> sectionAbsolutePathList = new ArrayList<>();
}
