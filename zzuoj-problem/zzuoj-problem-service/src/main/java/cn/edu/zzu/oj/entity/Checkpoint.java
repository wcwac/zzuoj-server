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
 * @since 2021-05-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Checkpoint implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "checkpoint_id", type = IdType.AUTO)
    private Long checkpointId;

    private Integer problemId;

    private String userId;

    private String isPrivate;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    private Integer inputSize;

    private Integer outputSize;

    private String inputName;

    private String outputName;

    /**
     * 扩展字段，暂不用
     */
    private String extension;


}
