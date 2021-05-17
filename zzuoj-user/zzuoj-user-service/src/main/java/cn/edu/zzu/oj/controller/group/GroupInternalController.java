package cn.edu.zzu.oj.controller.group;

import cn.edu.zzu.oj.Exceptions.BaseException;
import cn.edu.zzu.oj.api.GroupInternalApi;
import cn.edu.zzu.oj.entity.Group;
import cn.edu.zzu.oj.enums.HttpStatus;
import cn.edu.zzu.oj.service.impl.GroupServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GroupInternalController implements GroupInternalApi {
    Logger log = LoggerFactory.getLogger(GroupInternalController.class);

    @Autowired
    GroupServiceImpl groupService;
}
