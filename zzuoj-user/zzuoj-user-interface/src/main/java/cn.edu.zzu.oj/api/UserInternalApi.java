package cn.edu.zzu.oj.api;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/internal/user")
public interface UserInternalApi {
    String SERVICE_NAME = "zzuoj-user";
}
