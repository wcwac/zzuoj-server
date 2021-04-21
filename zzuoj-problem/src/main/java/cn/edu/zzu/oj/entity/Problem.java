package cn.edu.zzu.oj.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author yuliuyuan
 * @since 2021-04-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Problem implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "problem_id", type = IdType.AUTO)
    private Integer problemId;

    private String title;

    private String description;

    private String input;

    private String output;

    private String sampleInput;

    private String sampleOutput;

    private String spj;

    private String hint;

    private String source;

    private LocalDateTime inDate;

    private Integer timeLimit;

    private Integer memoryLimit;

    private String defunct;

    private Integer accepted;

    private Integer submit;

    private Integer solved;
}
