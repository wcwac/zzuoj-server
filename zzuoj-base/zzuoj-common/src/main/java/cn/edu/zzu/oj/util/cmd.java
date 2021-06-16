package cn.edu.zzu.oj.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class cmd {
    public static StringBuilder callExec(String[] cmd, File file) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec(cmd,null,file);
        int i= process.waitFor();
        process.exitValue();

        StringBuilder result = new StringBuilder();
        String line = null;
        if(i==0){
            BufferedReader bufrIn = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
            while ((line = bufrIn.readLine()) != null) {
                result.append(line);
            }
        }else {
            BufferedReader bufrError = new BufferedReader(new InputStreamReader(process.getErrorStream(), "UTF-8"));
            while ((line = bufrError.readLine()) != null) {
                result.append(line).append('\n');
            }
        }
        return result;
    }
}
