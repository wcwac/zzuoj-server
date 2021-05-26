package cn.edu.zzu.oj.controller;

import cn.edu.zzu.oj.anotation.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/judge/internal")
public class JudgeInternalController {

    @GetMapping("/test")
    public String test(){
        return "hello test";
    }

}
