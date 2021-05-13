package cn.edu.zzu.oj.util;

import java.util.Arrays;
import java.util.List;

public class ContestUtil {
    public static Integer getLangMask(String problems){
        List<String> temp = Arrays.asList(problems.split("^"));
        Integer res = 0;
        for(int i=0; i<temp.size(); i++){
            switch ( temp.get(i) ){
                case "c":
                    res |= 1;
                    break;
                case "c++":
                    res |= 1<<1;
                case "java":
                    res |= 1<<2;
                case "python":
                    res |= 1<<3;
                case "go":
                    res |= 1<<4;
            }
        }
        return res;
    }
}
