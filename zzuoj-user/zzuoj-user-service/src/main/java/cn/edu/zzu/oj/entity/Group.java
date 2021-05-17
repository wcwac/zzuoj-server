package cn.edu.zzu.oj.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author yuliuyuan
 * @since 2021-05-12
 */

@TableName("`group`")
@Accessors(chain = true)
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class Group implements Serializable {

    private static final long serialVersionUID = 1L;

    private String groupId;

    private String users;


}
