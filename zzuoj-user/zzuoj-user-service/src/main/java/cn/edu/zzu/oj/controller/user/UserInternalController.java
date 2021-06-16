package cn.edu.zzu.oj.controller.user;

import cn.edu.zzu.oj.api.UserInternalApi;
import cn.edu.zzu.oj.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInternalController implements UserInternalApi {
    @Autowired
    UserServiceImpl userService;

    @Override
    public Integer increSubmitAc(String userId, boolean ac){
        return userService.increSubmitAc(userId, ac);
    }
}
