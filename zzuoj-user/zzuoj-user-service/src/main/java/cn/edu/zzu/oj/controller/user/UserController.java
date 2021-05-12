package cn.edu.zzu.oj.controller.user;

import cn.edu.zzu.oj.Exceptions.BaseException;
import cn.edu.zzu.oj.anotation.BaseResponse;
import cn.edu.zzu.oj.entity.ResponseResult;
import cn.edu.zzu.oj.entity.User;
import cn.edu.zzu.oj.entity.frontToWeb.UserFront;
import cn.edu.zzu.oj.entity.jwt.JwtModel;
import cn.edu.zzu.oj.enums.HttpStatus;
import cn.edu.zzu.oj.service.impl.PrivilegeServiceImpl;
import cn.edu.zzu.oj.service.impl.UserServiceImpl;
import cn.edu.zzu.oj.util.JWTUtil;
import cn.edu.zzu.oj.util.MD5Util;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
@BaseResponse
@RestController
@RequestMapping("/user")
public class UserController {
    private static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserServiceImpl userService;

    //user controller调用auth的service,有点突兀了，hha
    @Autowired
    PrivilegeServiceImpl privilegeService;

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
    @PostMapping("/login")
    @ResponseBody
    public ResponseResult login(HttpServletResponse response,
                                @RequestBody @NotNull Map<String, String> json,
                                @RequestHeader("user-agent") String userAgent) throws ApiException, JsonProcessingException {
        String username = null, password = null;
        JwtModel jwtModel = null;
        try {
            username = json.get("username");
            password = json.get("password");
            User user = userService.login(username, MD5Util.md5(password));
            if(user == null ) {
                return ResponseResult.err("登陆失败", null);
            }
            jwtModel = new JwtModel().setUserId(user.getUserId())
                    .setNickName(user.getNick())
                    .setEmail(user.getEmail());
//                    .setIpv4(user.getIp());
            //todo: auth、user目前在一个微服务，直接在当前controller通过auth的service请求权限
            Integer role = privilegeService.getPrivilegeByUserId(user.getUserId());
            jwtModel.setRole(role);
        } catch (Exception e) {
            log.error("login error: " + e);
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //签发5小时的token
        ObjectMapper objectMapper = new ObjectMapper();
        return ResponseResult.ok("登陆成功", JWTUtil.createJWT(jwtModel.getUserId(), "salix", objectMapper.writeValueAsString(jwtModel), 5 * 60 * 60L * 1000L));
    }
}
