package cn.edu.zzu.oj.entity.frontToWeb;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;


@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolutionFront {
    private Integer solutionId;

    private Integer problemId;

    private String userId;

    //耗费时间
    private Integer time;

    //内存
    private Integer memory;

    //提交时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date inDate;

    //结果
    private String result;


    private Integer language;

    private Integer contestId;

    private Integer valid;

    private Integer num;

    private Integer codeLength;

    private BigDecimal passRate;
}
