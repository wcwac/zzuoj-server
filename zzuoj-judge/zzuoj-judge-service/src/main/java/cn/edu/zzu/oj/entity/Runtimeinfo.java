package cn.edu.zzu.oj.entity;

import java.io.Serializable;

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
 * @since 2021-06-02
 */

@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Data
@EqualsAndHashCode(callSuper = false)
public class Runtimeinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer solutionId;

    private String error;


}
