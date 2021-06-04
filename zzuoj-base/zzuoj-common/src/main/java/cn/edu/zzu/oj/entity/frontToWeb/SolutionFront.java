package cn.edu.zzu.oj.entity.webToFront;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class SolutionWeb {
    private Integer solutionId;

    private Integer problemId;

    private String userId;

    //耗费时间
    private Integer time;

    //内存
    private Integer memory;

    //提交时间
    private Date inDate;

    //结果
    private Integer result;


    private Integer language;

    private Integer contestId;

    private Integer valid;

    private Integer num;

    private Integer codeLength;

    private BigDecimal passRate;
}
