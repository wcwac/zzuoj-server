package cn.edu.zzu.oj.util;

public class LangUtil {
    public static String getFileSuf(Integer i){
        if(i==1) return ".c";
        if(i==2) return ".c++";
        if(i==3) return ".java";
        if(i==4) return ".python";
        if(i==5) return ".go";
        return "";
    }
}
