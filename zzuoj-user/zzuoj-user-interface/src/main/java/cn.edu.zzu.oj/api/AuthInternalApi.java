package cn.edu.zzu.oj.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/internal/auth")
public interface AuthInternalApi {
    String SERVICE_NAME = "zzuoj-user";

    @GetMapping("/test")
    public String test();
}
