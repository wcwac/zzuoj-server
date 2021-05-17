package cn.edu.zzu.oj.controller;

import cn.edu.zzu.oj.Exceptions.BaseException;
import cn.edu.zzu.oj.anotation.BaseResponse;
import cn.edu.zzu.oj.entity.Problem;
import cn.edu.zzu.oj.enums.HttpStatus;
import cn.edu.zzu.oj.service.impl.ProblemServiceImpl;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@BaseResponse
@RestController
@RequestMapping("/admin/problem")
public class ProblemAdminController {

    private static Logger log = LoggerFactory.getLogger(ProblemAdminController.class);
    @Autowired
    ProblemServiceImpl problemService;

    @GetMapping("/delete")
    public String deleteProblemById(@RequestParam("problemId") Integer problemId){
        Integer cnt = 0;
        try {
            cnt = problemService.deleteProblemById(problemId);
        } catch (Exception e){
            log.error("delete problem by id error:" + e.toString());
            return "delete problem by id error";
        }
        return "delete problem by id success";
    }

    @PostMapping("/add")
    public String insertProblem(@RequestBody String jsonData){
        Map<String, Object> params = null;
        try {
            params = JSON.parseObject(jsonData, HashMap.class);
            Problem problem = new Problem().setTitle( (String) params.get("title") )
                    .setTimeLimit((Integer) params.get("timeLimit"))
                    .setMemoryLimit((Integer) params.get("memoryLimit"))
                    .setDescription((String) params.get("description"))
                    .setInput((String) params.get("inputDescription"))
                    .setOutput((String) params.get("outputDescription"))
                    .setSampleInput((String) params.get("input") )
                    .setSampleOutput((String) params.get("output"))
                    .setHint((String) params.get("hint"))
                    .setSpj((String) params.get("isSpecialJudge"))
                    .setSource((String) params.get("source"));
            problem.setInDate(new Date()).setAccepted(0).setSubmit(0);



            problemService.addProblem(problem);
        } catch (Exception e){
            log.error("add problem error: " + e.toString());
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return "Add problem success!";
    }

    @PostMapping("/switchDefunct")
    public String switchDefunctStatus(@RequestBody String jsonData){
        Map<String, Object> params = null;
        try {
            params = JSON.parseObject(jsonData, HashMap.class);
            Integer problemId = (Integer) params.get("pid");
            String status = (String) params.get("newStatus");
            problemService.switchDefunctStatusByPid(problemId, status);
        } catch (Exception e){
            log.error("switchDefunctStatus error: " + e.toString());
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return "update defunct status success";
    }

    public static void main(String[] args) {
        String temp = "/zzuoj/test/123";
        String[] res = temp.split("/");
        System.out.println(res[0]);
        System.out.println(res[1]);
        System.out.println(res[2]);
        System.out.println(res[3]);
    }

}
