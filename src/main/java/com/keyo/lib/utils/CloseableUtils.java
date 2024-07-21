package com.keyo.lib.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * Closeable工具类
 *
 * @author Keyo
 * @date 2024/5/3
 */
public class CloseableUtils {

    /**
     * 关闭
     *
     * @param closeable
     */
    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
