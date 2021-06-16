package cn.edu.zzu.oj.util;

import java.util.Date;

public class DateUtilY {
    public static int calLastedTime(Date begin, Date end){
        long a = end.getTime();
        long b = begin.getTime();
        return (int)((a - b) / 1000);
    }
}
