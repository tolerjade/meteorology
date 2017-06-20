package cn.ac.iscas.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by admin on 2017/6/14.
 */
public class DosUtil {
    public static void executeDosCommand(String workspace, String command) {
        try {
            ProcessBuilder builder = new ProcessBuilder("cmd", "/c", command);
            builder.directory(new File(workspace));
            builder.redirectErrorStream(true);
            Process p = builder.start();
            InputStream is = p.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            p.waitFor();
            if (p.exitValue() != 0) {
                System.out.println("执行命令失败！:" + command);
            }
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
