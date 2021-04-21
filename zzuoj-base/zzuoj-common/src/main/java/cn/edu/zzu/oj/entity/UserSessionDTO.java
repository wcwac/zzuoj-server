package cn.edu.zzu.oj.entity;


import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserSessionDTO extends BaseDTO {

    public static final String HEADER_KEY = "ZZUOJ";
    public static final String HEADER_VALUE_LOGOUT = "logout";

    private Long userId;
    private String username;
    private String nickname;
    private String email;
    private String studentId;
    private Integer emailVerified;
    private List<String> roles;

    private String ipv4;
    private String userAgent;

    public boolean userIdEquals(Long userId) {
        return this.userId != null && this.userId.equals(userId);
    }

    public boolean userIdNotEquals(Long userId) {
        return !userIdEquals(userId);
    }
}