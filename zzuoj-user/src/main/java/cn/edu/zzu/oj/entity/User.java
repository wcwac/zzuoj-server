package cn.edu.zzu.oj.entity;

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
 * @since 2021-03-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;

    private String email;

    private Integer submit;

    private Integer solved;

    private String defunct;

    private String ip;

    private LocalDateTime accesstime;

    private String password;

    private LocalDateTime regTime;

    private String nick;

    private String school;


}
