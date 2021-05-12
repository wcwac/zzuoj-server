package cn.edu.zzu.oj.controller;

import cn.edu.zzu.oj.Exceptions.BaseException;
import cn.edu.zzu.oj.anotation.BaseResponse;
import cn.edu.zzu.oj.converter.ConverterUtil;
import cn.edu.zzu.oj.entity.Privilege;
import cn.edu.zzu.oj.entity.User;
import cn.edu.zzu.oj.entity.webToFront.UserWeb;
import cn.edu.zzu.oj.enums.HttpStatus;
import cn.edu.zzu.oj.service.impl.PrivilegeServiceImpl;
import cn.edu.zzu.oj.service.impl.UserServiceImpl;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@BaseResponse
@RestController
@RequestMapping("/admin/user")
public class UserAdminController {
    private Logger log = LoggerFactory.getLogger(UserAdminController.class);

    @Autowired
    UserServiceImpl userService;

    @Autowired
    PrivilegeServiceImpl privilegeService;

    //pos表示的是相对于第一条记录的偏移，数据库中第一条记录pos为0，
    @GetMapping("/show")
    public List<UserWeb> show(@RequestParam("pos") Integer pos, @RequestParam("limit") Integer limit){
         List<UserWeb> res = null;
        try {
            List<User> list = userService.getUserPage(pos, limit);
            for(Integer i=0; i< list.size(); i++){
                list.get(i).setPassword(null);
            }
            List<String> temp = new ArrayList<>();
            for(int i=0; i<list.size(); i++) temp.add(list.get(i).getUserId());
            List<Privilege> roles = privilegeService.getPrivilegesByUserIds(temp);

            res = ConverterUtil.UserToUserWeb(list, roles);
        } catch (Exception e){
            log.error("show user list error: " + e.toString());
            return null;
        }
        return res;
    }

    @GetMapping("/cnt")
    public Integer getProblems(){
        Integer cnt = null;
        try {
            cnt = userService.getUserCnt();
        } catch (Exception e){
            log.error("get user cnt error: " + e.toString());
            return null;
        }
        return cnt;
    }


    @PostMapping("/switchDefunct")
    public String switchDefunctStatus(@RequestBody String jsonData){
        Map<String, Object> params = null;
        try {
            params = JSON.parseObject(jsonData, HashMap.class);
            String userId = (String) params.get("uid");
            String status = (String) params.get("newStatus");
            userService.switchDefunctStatusByUid(userId, status);
        } catch (Exception e){
            log.error("user switchDefunctStatus error: " + e.toString());
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return "update user defunct status success";
    }


}
