package cn.edu.zzu.oj.controller.group;

import cn.edu.zzu.oj.Exceptions.BaseException;
import cn.edu.zzu.oj.anotation.BaseResponse;
import cn.edu.zzu.oj.converter.ConverterUtil;
import cn.edu.zzu.oj.entity.Group;
import cn.edu.zzu.oj.entity.Privilege;
import cn.edu.zzu.oj.enums.HttpStatus;
import cn.edu.zzu.oj.service.impl.GroupServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/group")
@BaseResponse
public class GroupAdminController {
    Logger log = LoggerFactory.getLogger(GroupInternalController.class);

    @Autowired
    GroupServiceImpl groupService;

    @PostMapping("/add")
    public String addGroup(@RequestBody  Map<String, Object> jsonData) {
        String group_id = null, users = null;
        String res = null;
        try {
            group_id = (String) jsonData.get("groupId");
            users = (String) jsonData.get("users");

            System.out.println("0))))))");
            System.out.println(group_id);
            System.out.println(users);
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

    @GetMapping("/show")
    public List<String> show(@RequestParam("pos") Integer pos, @RequestParam("limit") Integer limit){
        List<String> res = new ArrayList<>();
        try {
            List<Group> temp = groupService.getGroupPage(pos, limit);
            for(int i=0;i<temp.size(); i++){
                res.add(temp.get(i).getGroupId());
            }
        } catch (Exception e){
            log.error("show group list error: " + e.toString());
            return null;
        }
        return res;
    }

    @GetMapping("/cnt")
    public Integer getGroupCnt(){
        Integer cnt = null;
        try {
            cnt = groupService.getGroupCnt();
        } catch (Exception e){
            log.error("get group cnt error: " + e.toString());
            return null;
        }
        return cnt;
    }

    @GetMapping("/get")
    public Group getGroupById(@RequestParam String groupId){
        Group res = null;
        try {
            res = groupService.getGroupById(groupId);
        } catch (Exception e){
            log.error("get group cnt error: " + e.toString());
            return null;
        }
        return res;
    }

    @PostMapping("/update")
    public String updateGroupById(@RequestBody  Map<String, Object> jsonData){
        String res = null;
        try {
            String groupId = (String) jsonData.get("groupId");
            String users = (String) jsonData.get("users");
            if(groupService.updateGroupById( new Group(groupId, users) )==1) {
                res = "update group success";
            } else {
                res = "update group error";
            }
        } catch (Exception e){
            log.error("get group cnt error: " + e.toString());
            return null;
        }
        return res;
    }
}
