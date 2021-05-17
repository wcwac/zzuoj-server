package cn.edu.zzu.oj.controller.auth;
import cn.edu.zzu.oj.anotation.BaseResponse;
import cn.edu.zzu.oj.api.AuthInternalApi;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthInternalController implements AuthInternalApi {
    @Override
    public String test(){
        return "hello test api ok";
    }
}
