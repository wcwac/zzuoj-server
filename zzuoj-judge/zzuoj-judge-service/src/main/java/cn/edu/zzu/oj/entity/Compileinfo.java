package cn.edu.zzu.oj.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author yuliuyuan
 * @since 2021-06-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Compileinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer solutionId;

    private String error;


}
