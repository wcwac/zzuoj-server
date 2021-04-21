package cn.edu.zzu.oj.controller;


import cn.edu.zzu.oj.Exceptions.BaseException;
import cn.edu.zzu.oj.anotation.BaseResponse;
import cn.edu.zzu.oj.converter.WebEntityToFrontEntity;
import cn.edu.zzu.oj.entity.Problem;
import cn.edu.zzu.oj.entity.front.ProblemFront;
import cn.edu.zzu.oj.enums.HttpStatus;
import cn.edu.zzu.oj.service.impl.ProblemServiceImpl;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sun.tools.jconsole.JConsole;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yuliuyuan
 * @since 2021-04-11
 */

@BaseResponse
@Log4j2
@RestController
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    ProblemServiceImpl problemService;

    //pos表示的是相对于第一条记录的偏移，数据库中第一条记录pos为0，
    @GetMapping("/show")
    public List<ProblemFront> show(@RequestParam("pos") Integer pos, @RequestParam("limit") Integer limit){
        List<Problem> list = null;
        try {
            list = problemService.getProblemsPage(pos, limit);
        } catch (Exception e){
            log.error("show problems list error: " + e.toString());
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return WebEntityToFrontEntity.ProblemToProblemFront(list);
    }

    @GetMapping("/cnt")
    public Integer getProblems(){
        return problemService.getProblemCnt();
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

    @GetMapping("/test")
    public String test(){
        return "problem service is success!";
    }

    @PostMapping("/isdefunct")
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
}
