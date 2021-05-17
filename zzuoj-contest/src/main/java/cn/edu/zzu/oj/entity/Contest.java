package cn.edu.zzu.oj.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author yuliuyuan
 * @since 2021-05-13
 */

@Accessors(chain = true)
@Data
@EqualsAndHashCode(callSuper = false)
public class Contest implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "contest_id", type = IdType.AUTO)
    private Integer contestId;

    private String title;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    private String defunct;

    private String description;

    private Integer isPrivate;

    /**
     * bits for LANG to mask
     */
    private Integer langmask;

    private String password;

    private String groupId;

    private String problems;

    private String userId;
}
