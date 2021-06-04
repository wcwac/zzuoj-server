package cn.edu.zzu.oj.controller;

import cn.edu.zzu.oj.api.JudgeInternalApi;
import cn.edu.zzu.oj.entity.SourceCode;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
public class JudgeInternalController implements JudgeInternalApi {

    @Override
    public Integer judge(SourceCode sourceCode) throws IOException, InterruptedException {
        File file = new File("/Users/bytedance/yly/zzuoj");
        String main  = "./zzuoj-judge/deer-executor/main.go";

        String checkPoints = "deer-executor/data/problems/APlusB/problem.json";
        String code = "./code/"+sourceCode.getUserId()+"/"+sourceCode.getProblemId();
        String[] cmds3 = {"go","run",main, "run",checkPoints,code};
        System.out.println(callExec(cmds3,file));
        return 1;
    }

    public static StringBuilder callExec(String[] cmd,File file) throws IOException, InterruptedException {
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
