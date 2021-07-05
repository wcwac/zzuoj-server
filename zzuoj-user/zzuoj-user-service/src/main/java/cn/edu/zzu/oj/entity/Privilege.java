package cn.edu.zzu.oj.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author yuliuyuan
 * @since 2021-05-04
 */

@Accessors(chain = true)
@Data
@EqualsAndHashCode(callSuper = false)
public class Privilege implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;

    //二进制 第一位表示是否有root权限，第二位:admin权限
    private Integer rightstr;

    //这个字段暂时不用，去掉权限直接在数据库里删除记录即可
    private String defunct;


}
