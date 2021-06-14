package cn.edu.zzu.oj.util;

import cn.edu.zzu.oj.anotation.UserSession;
import cn.edu.zzu.oj.entity.jwt.UserSessionDTO;

public class UserSessionUtil {
    public static boolean isAdmin(UserSessionDTO userSessionDTO){
        return userSessionDTO.getRole() != 0;
    }

    public static boolean isRoot(UserSessionDTO userSessionDTO){
        return userSessionDTO.getRole() == 1;
    }
}
