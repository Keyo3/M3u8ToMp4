package com.keyo;

import com.keyo.lib.utils.ListUtils;
import com.keyo.log.Logger;
import com.keyo.m3u8tomp4.M3u8ToMp4;
import com.keyo.transfer.FileTransfer;
import com.keyo.transfer.FileTransferResult;
import com.keyo.transfer.M3U8FileTransferToMp4;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Keyo
 * @date 2024/5/3
 */
public class Main {

    private static void transfer() throws Exception {
        M3u8ToMp4 m3u8ToMp4 = new M3u8ToMp4();
        m3u8ToMp4.transfer();
    }

    private static void newTransfer() throws Exception {
        FileTransfer fileTransfer = new M3U8FileTransferToMp4();
        List<FileTransferResult> fileTransferResults = fileTransfer.fileTransfer();

        List<String> success = new ArrayList<>();
        List<String> fail = new ArrayList<>();
        for (FileTransferResult fileTransferResult : fileTransferResults) {
            if (fileTransferResult.isSuccess()) {
                success.add(fileTransferResult.getFileName());
            } else {
                fail.add(fileTransferResult.getFileName());
            }
        }

        Logger logger = Logger.logger;
        logger.log("转换成功：" + ListUtils.transferString(success, ", "));
        logger.log("转换失败：" + ListUtils.transferString(fail, ", "));
    }

    public static void main(String[] args) throws Exception {
//        transfer();
        newTransfer();
    }
}