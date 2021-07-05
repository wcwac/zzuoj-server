package cn.edu.zzu.oj.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @since 2021-05-18
 */

@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class Checkpoint implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "checkpoint_id", type = IdType.AUTO)
    private Long checkpointId;

    private Integer problemId;

    private String userId;

    private String isPrivate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date modifyTime;

    private Long inputSize;

    private Long outputSize;

    private String inputName;

    private String outputName;

    /**
     * 扩展字段，暂不用
     */
    private String extension;


}
