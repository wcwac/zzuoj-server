package cn.edu.zzu.oj.controller;
import cn.edu.zzu.oj.anotation.BaseResponse;
import cn.edu.zzu.oj.api.AuthInternalApi;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthInternalController implements AuthInternalApi {
    @Override
    public String test(){
        System.out.println("test api ok");
        return "hello test api ok";
    }
}
