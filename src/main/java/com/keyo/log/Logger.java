package com.keyo.log;

import com.keyo.lib.utils.CloseableUtils;
import com.keyo.lib.utils.FileUtils;
import com.keyo.lib.utils.PathUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author Keyo
 * @date 2024/7/21
 */
public class Logger {

    public static final Logger logger;

    static {
        try {
            logger = new Logger();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private FileOutputStream fileOutputStream = null;

    private Logger() throws IOException {
        String projectPath = PathUtils.getProjectPath();
        String absolutePath = PathUtils.concatPath(projectPath, "log.txt");

        if (!FileUtils.createAndCheckFile(absolutePath)) {
            System.exit(0);
        }

        fileOutputStream = new FileOutputStream(absolutePath, true);
    }

    /**
     * ¼ÇÂ¼ÈÕÖ¾
     *
     * @param log
     */
    public void log(String log) {
        System.out.println(log);

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = simpleDateFormat.format(date);

        log = time + "\t" + log + "\n";
        try {
            fileOutputStream.write(log.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        CloseableUtils.close(fileOutputStream);
    }
}