package cn.edu.zzu.oj.controller;


import cn.edu.zzu.oj.anotation.BaseResponse;
import cn.edu.zzu.oj.anotation.UserSession;
import cn.edu.zzu.oj.converter.EntityToFront;
import cn.edu.zzu.oj.entity.ContestT;
import cn.edu.zzu.oj.entity.frontToWeb.ContestFront;
import cn.edu.zzu.oj.entity.jwt.UserSessionDTO;
import cn.edu.zzu.oj.service.impl.ContestServiceImpl;
import cn.edu.zzu.oj.util.UserSessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yuliuyuan
 * @since 2021-05-12
 */
@BaseResponse
@RestController
@RequestMapping("/contest")
public class ContestController {
    private static Logger log = LoggerFactory.getLogger(ContestController.class);

    @Autowired
    ContestServiceImpl contestService;

    //pos表示的是相对于第一条记录的偏移，数据库中第一条记录pos为0，
    @GetMapping("/show")
    public List<ContestT> show(@RequestParam("pos") Integer pos, @RequestParam("limit") Integer limit, @UserSession UserSessionDTO userSessionDTO){
        List<ContestT> list = null;
        try {
            list = contestService.getContestsByPage(pos, limit);

            System.out.println(userSessionDTO);
            if(userSessionDTO == null || UserSessionUtil.isAdmin(userSessionDTO)){
                for(int i=0 ; i<list.size(); i++){
                    list.get(i).setGroupId(null).setPassword(null).setProblems(null);
                }
            }
        } catch (Exception e){
            log.error("show contests list error: " + e.getMessage());
            return null;
        }
        return list;
    }

    @GetMapping("/cnt")
    public Integer getContests(@UserSession UserSessionDTO userMeta){
        System.out.println(userMeta);
        return contestService.getContestCnt();
    }


    @GetMapping("/get")
    public ContestFront getContestById(@RequestParam("contestId") Integer contestId, @UserSession UserSessionDTO userSessionDTO) {
        ContestFront contestFront = null;
        try {
            ContestT contestT = contestService.getContestByContestId(contestId);
            contestFront = EntityToFront.ContestToContestFront(contestT);

            if(!UserSessionUtil.isAdmin(userSessionDTO)){
                contestFront.setGroupId(null).setPassword(null).setProblems(null);
            }
        } catch (Exception e){
            return null;
        }
        return contestFront;
    }


}
