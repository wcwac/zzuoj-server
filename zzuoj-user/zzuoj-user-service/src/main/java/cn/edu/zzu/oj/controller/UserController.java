package cn.edu.zzu.oj.controller;

import cn.edu.zzu.oj.anotation.BaseResponse;
import cn.edu.zzu.oj.entity.ResponseResult;
import cn.edu.zzu.oj.entity.User;
import cn.edu.zzu.oj.entity.UserSessionDTO;
import cn.edu.zzu.oj.entity.front.UserFront;
import cn.edu.zzu.oj.service.impl.UserServiceImpl;
import cn.edu.zzu.oj.util.MD5Util;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.Map;

@BaseResponse
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
    public String registry(@Valid @RequestBody UserFront userFront){
        User user = new User().setUserId(userFront.getUserId())
                                .setEmail(userFront.getEmail())
                                .setPassword( MD5Util.md5(userFront.getPassword()) )
                                .setNick(userFront.getNickname())
                                .setSchool(userFront.getSchool());
        user.setSubmit(0)
                .setSolved(0)
                .setDefunct("N")
                .setIp(null)
                .setAccesstime(new Date())
                .setRegTime(new Date());

        if( userService.registry(user) ){
            return "注册成功!";
        } else{
            return "注册失败，可能是账号重复或者网络问题";
        }
    }
//
//    @PostMapping("/login")
//    @ResponseBody
//    public UserSessionDTO login(HttpServletResponse response,
//                                                @RequestBody @NotNull Map<String, String> json,
//                                                @RequestHeader("user-agent") String userAgent) throws ApiException {
//        String username = null, password = null;
//        try {
//            username = json.get("username");
//            password = json.get("password");
//        } catch (Exception e) {
//
//        }
//    }
}
