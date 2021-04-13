package cn.edu.zzu.oj.controller;

import cn.edu.zzu.oj.entity.User;
import cn.edu.zzu.oj.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @GetMapping("/test")
    public String test(){
        return "hello salix";
    }

    @Transactional
    @PostMapping("/registry")
    public String registry(@Valid @RequestBody User user){

        if( userService.registry(user) ){
            return "注册成功!";
        } else{
            return "注册失败，可能是账号重复或者网络问题";
        }
    }
}
