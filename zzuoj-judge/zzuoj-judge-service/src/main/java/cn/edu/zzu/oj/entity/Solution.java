package cn.edu.zzu.oj.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author yuliuyuan
 * @since 2021-06-02
 */

@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Data
@EqualsAndHashCode(callSuper = false)
public class Solution implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "solution_id", type = IdType.AUTO)
    private Integer solutionId;

    private Integer problemId;

    private String userId;

    private Integer time;

    private Integer memory;

    private Date inDate;

    private Integer result;

    private Integer language;

    private Integer contestId;

    private Integer valid;

    private Integer num;

    private Integer codeLength;

    private BigDecimal passRate;

}
