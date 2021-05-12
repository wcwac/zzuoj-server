package cn.edu.zzu.oj.controller;

import cn.edu.zzu.oj.anotation.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@BaseResponse
@RestController
@RequestMapping("/admin/contest")
public class ContestAdminController {
    private static Logger log = LoggerFactory.getLogger(ContestAdminController.class);

    @PostMapping("/add")
    public String addContest(@RequestBody Map<String,Object> jsonData){
        return "ok";
    }
}
