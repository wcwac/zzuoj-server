package cn.edu.zzu.oj.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContestUtil {
    static String[] langString = new String[]{"c","c++","java","python","go"};
    static Integer[] langInteger = new Integer[]{1,1<<1,1<<2,1<<3,1<<4};

    public static Integer getLangMask(List<String> lang){
        Integer res = 0;
        for(int i=0; i<lang.size(); i++){
            for(int j=0;j<langString.length; j++){
                if( lang.get(i).equals(langString[j]) ){
                    res |= langInteger[j];
                    break;
                }
            }
        }
        return res;
    }

    public static List<String> getLangString(Integer lang){
        List<String> res = new ArrayList<>();
        for(int i=0; i<langInteger.length; i++){
            for(int j=0; j<langInteger.length;  j++){
                if( (lang & langInteger[j]) !=0 ){
                    lang -= langInteger[j];
                    res.add( langString[j] );
                    break;
                }
            }
        }
        return res;
    }
}
