package cn.edu.zzu.oj.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 
 * </p>
 *
 * @author yuliuyuan
 * @since 2021-03-31
 */

@Accessors(chain = true)
@Data
@EqualsAndHashCode(callSuper = false)
public class News implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "news_id", type = IdType.AUTO)
    private Integer newsId;

    private String userId;

    private String title;

    private String content;

    private Date time;

    private Integer importance;

    private String defunct;
}
