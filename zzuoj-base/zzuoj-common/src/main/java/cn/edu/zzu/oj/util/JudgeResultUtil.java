package cn.edu.zzu.oj.util;

import cn.edu.zzu.oj.enums.CodeStatus;
import com.alibaba.fastjson.JSONObject;
import com.sun.tools.javac.jvm.Code;

import java.util.Map;

public class JudgeResultUtil {
    public static CodeStatus parseResult(String s){
        Integer i = 0;
        if(s.startsWith("exit status ")){
            String temp = s.split(" ")[2];
            temp = temp.split("\n")[0];
            System.out.println(temp);
            i = Integer.parseInt(temp);
        }
        return CodeStatus.getCodeStatusById(i);
    }

    //res[0]表示的是多少ms，res[1]表示内存
    public static Integer[] parseAc(String s){
        Integer[] res = new Integer[2];
        JSONObject jsonObject = JSONObject.parseObject(s);
        Map<String,Object> m = (Map<String, Object>) jsonObject.get("data");
        res[0] = (Integer) m.get("time_used");
        res[1] = (Integer) m.get("memory_used");

        return res;
    }
}
