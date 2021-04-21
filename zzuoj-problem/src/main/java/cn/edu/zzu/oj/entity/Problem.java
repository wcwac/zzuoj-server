package cn.edu.zzu.oj.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author yuliuyuan
 * @since 2021-04-11
 */


@ToString
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName(autoResultMap = true)
public class Problem implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "problem_id", type = IdType.AUTO)
    private Integer problemId;

    private String title;

    private String description;

    //输入说明
    private String input;

    //输出说明
    private String output;

    private String sampleInput;

    private String sampleOutput;

    private String spj;

    private String hint;

    private String source;

    private Date inDate;

    private Integer timeLimit;

    private Integer memoryLimit;

    private String defunct;

    private Integer accepted;

    private Integer submit;

    private Integer solved;
}
