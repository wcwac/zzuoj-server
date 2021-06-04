package cn.edu.zzu.oj.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Accessors(chain = true)
@NoArgsConstructor
@Data
public class SourceT {
    private Integer solutionId;

    private Integer problemId;

    private String userId;

    private Date inDate;

    private Integer language;

    private Integer contestId;

    private Integer valid;

    private Integer num;

    private Integer codeLength;

    private String filePath;
}
