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
 * @since 2021-05-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Contest implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "contest_id", type = IdType.AUTO)
    private Integer contestId;

    private String title;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String defunct;

    private String description;

    private Integer isPrivate;

    /**
     * bits for LANG to mask
     */
    private Integer langmask;

    private String password;

    private Integer groupId;


}
