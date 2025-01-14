package cn.edu.zzu.oj.controller.user;

import cn.edu.zzu.oj.Exceptions.BaseException;
import cn.edu.zzu.oj.anotation.BaseResponse;
import cn.edu.zzu.oj.entity.ResponseResult;
import cn.edu.zzu.oj.entity.User;
import cn.edu.zzu.oj.entity.frontToWeb.UserFront;
import cn.edu.zzu.oj.entity.jwt.UserSessionDTO;
import cn.edu.zzu.oj.enums.HttpStatus;
import cn.edu.zzu.oj.service.impl.PrivilegeServiceImpl;
import cn.edu.zzu.oj.service.impl.UserServiceImpl;
import cn.edu.zzu.oj.util.JWTUtil;
import cn.edu.zzu.oj.util.MD5Util;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yuliuyuan
 * @since 2021-05-04
 */
@Slf4j
@BaseResponse
@RestController
@RequestMapping("/user")
public class UserController {
//    private static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserServiceImpl userService;

    //user controller调用auth的service,有点突兀了，hha
    @Autowired
    PrivilegeServiceImpl privilegeService;

//    @Transactional
//    @PostMapping("/registry")
//    public String registry(@Valid @RequestBody UserFront userFront){
//        User user = new User().setUserId(userFront.getUserId())
//                .setEmail(userFront.getEmail())
//                .setPassword( MD5Util.md5(userFront.getPassword()) )
//                .setNick(userFront.getNickname())
//                .setSchool(userFront.getSchool());
//        user.setSubmit(0)
//                .setSolved(0)
//                .setDefunct("N")
//                .setIp(null)
//                .setAccesstime(new Date())
//                .setRegTime(new Date());
//
//        if( userService.registry(user) ){
//            return "注册成功!";
//        } else{
//            return "注册失败，可能是账号重复或者网络问题";
//        }
//    }

    @PostMapping("/registry")
    public ResponseResult registry(@Valid @RequestBody UserFront userFront, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseResult.err("注册失败，" + result.getAllErrors().get(0).getDefaultMessage(), null);
        }
        User user = new User().setUserId(userFront.getUserId())
                .setEmail(userFront.getEmail())
                .setPassword(MD5Util.md5(userFront.getPassword()))
                .setNick(userFront.getNickname())
                .setSchool(userFront.getSchool());
        user.setSubmit(0)
                .setSolved(0)
                .setDefunct("N")
                .setIp(null)
                .setAccesstime(new Date())
                .setRegTime(new Date());
        if (userService.registry(user)) {
            ObjectMapper objectMapper = new ObjectMapper();
            return ResponseResult.ok("注册成功!", null);
        } else {
            return ResponseResult.err("注册失败，该学号已注册", null);
        }
    }

    //
    @PostMapping("/login")
    public ResponseResult login(HttpServletResponse response,
                                @RequestBody @NotNull Map<String, String> json,
                                @RequestHeader("user-agent") String userAgent) throws ApiException, JsonProcessingException {
        String username = null, password = null;
        UserSessionDTO userSessionDTO = null;
        try {
            username = json.get("username");
            password = json.get("password");
            User user = userService.login(username, MD5Util.md5(password));
            if(user == null ) {
                return ResponseResult.err("登陆失败", null);
            }
            userSessionDTO = new UserSessionDTO().setUserId(user.getUserId())
                    .setNickName(user.getNick())
                    .setEmail(user.getEmail());
//                    .setIpv4(user.getIp());
            //todo: auth、user目前在一个微服务，直接在当前controller通过auth的service请求权限
            Integer role = privilegeService.getPrivilegeByUserId(user.getUserId());
            userSessionDTO.setRole(role);
        } catch (Exception e) {
            log.error("login error: " + e);
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //签发5小时的token
        ObjectMapper objectMapper = new ObjectMapper();
        return ResponseResult.ok("登陆成功", JWTUtil.createJWT(userSessionDTO.getUserId(), "salix", objectMapper.writeValueAsString(userSessionDTO),  5 * 60 * 60L * 1000L));
    }

    @GetMapping("/get")
    public User getUserById(@RequestParam String userId){
        User user = null;
        try {
            user = userService.getUserById(userId);

            user.setPassword(null).setIp(null).setAccesstime(null).setDefunct(null);
        } catch (Exception e){
            log.error("get user by userId error: " + e.toString());
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return user;
    }

    @PostMapping("/update")
    public String updateUserById(@RequestBody User user){
        try {
            userService.updateUserByUserId(user);
        } catch (Exception e){
            log.error("get user by userId error: " + e.toString());
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return "update profile by userId success";
    }

}
