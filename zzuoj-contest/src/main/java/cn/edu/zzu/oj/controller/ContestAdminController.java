package cn.edu.zzu.oj.controller;

import cn.edu.zzu.oj.Exceptions.BaseException;
import cn.edu.zzu.oj.anotation.BaseResponse;
import cn.edu.zzu.oj.entity.Contest;
import cn.edu.zzu.oj.entity.frontToWeb.ContestFront;
import cn.edu.zzu.oj.enums.HttpStatus;
import cn.edu.zzu.oj.service.impl.ContestServiceImpl;
import cn.edu.zzu.oj.util.ContestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@BaseResponse
@RestController
@RequestMapping("/admin/contest")
public class ContestAdminController {
    private static Logger log = LoggerFactory.getLogger(ContestAdminController.class);

    @Autowired
    ContestServiceImpl contestService;

    @PostMapping("/add")
    public String addContest(@RequestBody ContestFront contestFront){
        Integer  cnt = 0;
        try {
            Contest contest = new Contest().setTitle( contestFront.getTitle() )
                    .setStartTime( contestFront.getTime().get(0) )
                    .setEndTime( contestFront.getTime().get(1) )
                    .setDefunct( contestFront.getDefunct() )
                    .setDescription( contestFront.getDescription() )
                    .setIsPrivate( contestFront.getIsPrivate() )
                    .setLangmask( ContestUtil.getLangMask(contestFront.getProblems()) )
                    .setPassword( contestFront.getPassword() )
                    .setGroupId( contestFront.getGroupId() )
                    .setProblems( contestFront.getProblems() );
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
}
