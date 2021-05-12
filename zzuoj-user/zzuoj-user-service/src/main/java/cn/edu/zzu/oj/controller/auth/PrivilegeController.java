package cn.edu.zzu.oj.controller.auth;


import cn.edu.zzu.oj.Exceptions.BaseException;
import cn.edu.zzu.oj.enums.HttpStatus;
import cn.edu.zzu.oj.service.impl.PrivilegeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yuliuyuan
 * @since 2021-05-04
 */
@RestController
@RequestMapping("/auth")
public class PrivilegeController {
    private Logger log = LoggerFactory.getLogger(PrivilegeController.class);

    @Autowired
    private PrivilegeServiceImpl privilegeService = null;

    @GetMapping("/get")
    public Integer getPrivilegeByUId(@RequestParam String uId){
        Integer res = null;
        try {
            res = privilegeService.getPrivilegeByUserId(uId);
        } catch (Exception e){
            log.error("get privilege error: " + e.getMessage());
            throw new BaseException(HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
        }
        return res;
    }
}