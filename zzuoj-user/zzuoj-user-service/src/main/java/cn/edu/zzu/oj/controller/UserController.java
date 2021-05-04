package cn.edu.zzu.oj.controller;

import cn.edu.zzu.oj.Exceptions.BaseException;
import cn.edu.zzu.oj.anotation.BaseResponse;
import cn.edu.zzu.oj.entity.User;
import cn.edu.zzu.oj.entity.front.UserFront;
import cn.edu.zzu.oj.entity.jwt.JwtModel;
import cn.edu.zzu.oj.enums.HttpStatus;
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
    public String login(HttpServletResponse response,
                        @RequestBody @NotNull Map<String, String> json,
                        @RequestHeader("user-agent") String userAgent) throws ApiException, JsonProcessingException {
        String username = null, password = null;
        JwtModel jwtModel = null;
        try {
            username = json.get("username");
            password = json.get("password");
            User user = userService.login(username, MD5Util.md5(password));
            jwtModel = new JwtModel().setUserId(user.getUserId())
                    .setNickname(user.getNick())
                    .setEmail(user.getEmail());
//                    .setIpv4(user.getIp());
            //todo: 将角色权限控制放在user表？ 为了扩展性，可以不这样做，但是需要连表查询
//            userSessionDTO.setRole();
        } catch (Exception e) {
            log.error("login error: " + e);
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //签发30分钟的token
        ObjectMapper objectMapper = new ObjectMapper();
        return JWTUtil.createJWT(jwtModel.getUserId(), "salix", objectMapper.writeValueAsString(jwtModel), 30 * 60L * 1000L);
    }


}
