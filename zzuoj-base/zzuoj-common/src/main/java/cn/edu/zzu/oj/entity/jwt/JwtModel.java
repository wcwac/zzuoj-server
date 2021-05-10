package cn.edu.zzu.oj.entity.jwt;


import cn.edu.zzu.oj.entity.BaseDTO;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors(chain = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class JwtModel extends BaseDTO {

    public static final String HEADER_KEY = "ZZUOJUserInfo";
    public static final String HEADER_VALUE_LOGOUT = "logout";

    private String userId;
    private String nickName;
    private String email;
    //0表示root 1表示admin 2表示user
    private Integer role;

//    private String ipv4;
}