package com.keyo.lib.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * Closeable������
 *
 * @author Keyo
 * @date 2024/5/3
 */
public class CloseableUtils {

    /**
     * �ر�
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
