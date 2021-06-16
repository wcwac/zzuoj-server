package cn.edu.zzu.oj.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/internal/user")
public interface UserInternalApi {
    String SERVICE_NAME = "ZZUOj-USER";

    @GetMapping("/increSubmitAc")
    public Integer increSubmitAc(@RequestParam("userId") String userId,@RequestParam("ac")  boolean ac);
}
