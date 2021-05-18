package cn.edu.zzu.oj.controller;

import cn.edu.zzu.oj.Exceptions.BaseException;
import cn.edu.zzu.oj.anotation.BaseResponse;
import cn.edu.zzu.oj.converter.FrontToEntity;
import cn.edu.zzu.oj.entity.Contest;
import cn.edu.zzu.oj.entity.frontToWeb.ContestFront;
import cn.edu.zzu.oj.enums.HttpStatus;
import cn.edu.zzu.oj.service.impl.ContestServiceImpl;
import cn.edu.zzu.oj.util.ContestUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Slf4j
@BaseResponse
@RestController
@RequestMapping("/admin/contest")
public class ContestAdminController {
//    private static Logger log = LoggerFactory.getLogger(ContestAdminController.class);

    @Autowired
    ContestServiceImpl contestService;

    @PostMapping("/add")
    public String addContest(@RequestBody ContestFront contestFront){
        Integer  cnt = 0;
        try {
            Contest contest = FrontToEntity.ContestFrontToContest(contestFront);
            cnt = contestService.addContest(contest);
        } catch (Exception e) {
            log.error("add contest fail: "+ e.getMessage());
            throw new BaseException(HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
        }
        if(1 == cnt){
            return "add contest success";
        } else {
            return "add contest fail";
        }
    }

//     ("contestId") Integer contestId, @RequestParam("newStatus") String newStatus
    @PostMapping("/switchDefunct")
    public String switchContestDefunctStatus(@RequestBody Map<String,Object> jsonData){
        Integer cnt = 0;
        try {
            Integer contestId = (Integer) jsonData.get("contestId");
            String newStatus = (String) jsonData.get("newStatus");
            cnt = this.contestService.updateContestByContestId(new Contest().setContestId(contestId).setDefunct(newStatus));
        } catch (Exception e) {
            log.error("update contest defunct fail: "+ e.getMessage());
            throw new BaseException(HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
        }
        if(1 == cnt){
            return "update contest defunct success";
        } else {
            return "update contest defunct fail";
        }
    }

    //todo update私有共有的时候 需要设置其他字段是否为空
    @PostMapping("/update")
    public String updateContestById(@RequestBody ContestFront contestFront){
        Integer cnt = 0;
        try {
            cnt = contestService.updateContestByContestId(FrontToEntity.ContestFrontToContest(contestFront) );
        } catch (Exception e) {
            log.error("update contest fail: "+ e.getMessage());
            throw new BaseException(HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
        }
        if(1 == cnt){
            return "update contest success";
        } else {
            return "update contest fail";
        }
    }

    @GetMapping("/delete")
    public String deleteContestById(@RequestParam("contestId") Integer contestId){
        Integer cnt = 0;
        try {
            cnt = contestService.deleteContestByContestId( contestId );
        } catch (Exception e) {
            log.error("delete contest fail: "+ e.getMessage());
            throw new BaseException(HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
        }
        if(1 == cnt){
            return "delete contest success";
        } else {
            return "delete contest fail";
        }
    }
}
