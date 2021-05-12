package cn.edu.zzu.oj.controller.auth;


import cn.edu.zzu.oj.Exceptions.BaseException;
import cn.edu.zzu.oj.anotation.BaseResponse;
import cn.edu.zzu.oj.enums.HttpStatus;
import cn.edu.zzu.oj.service.impl.PrivilegeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@BaseResponse
@RestController
@RequestMapping("/root/auth")
public class PrivilegeRootController {
    private Logger log = LoggerFactory.getLogger(PrivilegeRootController.class);

    @Autowired
    private PrivilegeServiceImpl privilegeService = null;

    @GetMapping("/updateRole")
    public String updatePrivilege(@RequestParam String userId,@RequestParam Integer newPrivilege) {
        Integer res = 0;
        try {
            privilegeService.deletePrivilegeByUserId(userId);
            privilegeService.addPrivilegeByUserId(userId, newPrivilege);
        } catch (Exception e){
            log.error("update privilege error: %s uId:%s", e.getMessage(), userId) ;
            throw new BaseException(HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
        }
        return "update privilege success";
    }
}
