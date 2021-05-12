package cn.edu.zzu.oj.controller.group;

import cn.edu.zzu.oj.Exceptions.BaseException;
import cn.edu.zzu.oj.anotation.BaseResponse;
import cn.edu.zzu.oj.entity.Group;
import cn.edu.zzu.oj.enums.HttpStatus;
import cn.edu.zzu.oj.service.impl.GroupServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin/group")
@BaseResponse
public class GroupAdminController {
    Logger log = LoggerFactory.getLogger(GroupInternalController.class);

    @Autowired
    GroupServiceImpl groupService;

    @PostMapping("/add")
    public String addGroup(Map<String, Object> jsonData) {
        String group_id = null, users = null;
        String res = null;
        try {
            group_id = (String) jsonData.get("groupId");
            users = (String) jsonData.get("users");
            if(groupService.addGroup(new Group(group_id,users)) == 0){
                res = "add group error!";
            } else {
                res = "add group success!";
            }
        } catch (BaseException e){
            log.error("add group error");
            throw new BaseException(HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
        }
        return res;
    }

    @GetMapping("/delete")
    public String deleteGroup(@RequestParam String groupId){
        String res = null;
        try {
            if(groupService.deleteGroup(groupId) == 0){
                res = "delete group error!";
            } else {
                res = "delete group success!";
            }
        } catch (BaseException e){
            log.error("delete group error");
            throw new BaseException(HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
        }
        return res;
    }
}
