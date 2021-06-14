package cn.edu.zzu.oj.controller.problem;

import cn.edu.zzu.oj.Exceptions.BaseException;
import cn.edu.zzu.oj.anotation.BaseResponse;
import cn.edu.zzu.oj.anotation.UserSession;
import cn.edu.zzu.oj.client.FileClient;
import cn.edu.zzu.oj.converter.WebEntityToFrontEntity;
import cn.edu.zzu.oj.entity.Problem;
import cn.edu.zzu.oj.entity.checkpoint.CheckPointConf;
import cn.edu.zzu.oj.entity.checkpoint.TestCaseDTO;
import cn.edu.zzu.oj.entity.frontToWeb.ProblemFront;
import cn.edu.zzu.oj.entity.jwt.UserSessionDTO;
import cn.edu.zzu.oj.enums.HttpStatus;
import cn.edu.zzu.oj.service.impl.CheckpointServiceImpl;
import cn.edu.zzu.oj.service.impl.ProblemServiceImpl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.catalina.User;
import org.omg.PortableInterceptor.INACTIVE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@BaseResponse
@RestController
@RequestMapping("/admin/problem")
public class ProblemAdminController {

    private static Logger log = LoggerFactory.getLogger(ProblemAdminController.class);
    @Autowired
    ProblemServiceImpl problemService;

    @Autowired
    FileClient fileClient;

    @Autowired
    CheckpointServiceImpl checkpointService;

    @GetMapping("/delete")
    public String deleteProblemById(@RequestParam("problemId") Integer problemId){
        Integer cnt = 0;
        try {
            cnt = problemService.deleteProblemById(problemId);

            //删除所有测试点（文件+所有测试点）
            if( fileClient.deleteFiles(String.valueOf(problemId)) ){
                log.info("delete checkpoints by pId success");
            } else {
                log.error("delete checkpoints by pId fail");
            }
            checkpointService.deleteAllPointsByPid(problemId);
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
                    .setSource((String) params.get("source"))
                    .setDefunct((String) params.get("defunct"));
            problem.setInDate(new Date()).setAccepted(0).setSubmit(0);
            int problemId = problemService.insert(problem);

            //创建测试点文件夹
            String base = "/Users/bytedance/yly/zzuoj/deer-executor/";
            String filePath = "data/problems/"+problemId;
            File file = new File(base + filePath);
            if(!file.exists()){
                file.mkdir();
            }
            //创建problen.json文件
            File file1 = new File(base + filePath + "/problem.json");
            if(!file1.exists()){
                file.createNewFile();
            }
            CheckPointConf checkPointConf = new CheckPointConf().setTestCaseList(new ArrayList<TestCaseDTO>())
                    .setTime_limit(problem.getTimeLimit())
                    .setMemory_limit(problem.getMemoryLimit())
                    .setReal_time_limit(problem.getTimeLimit()*4)
                    .setFile_size_limit(52428800)
                    .setUid(-1)
                    .setStrict_mode(true);
            String temp = JSONObject.toJSONString(checkPointConf);
            BufferedWriter out = new BufferedWriter(new FileWriter(base + filePath + "/problem.json"));
            out.write(temp);
            out.close();
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


    @PostMapping("/update")
    public Integer updateProblem(@RequestBody Problem problem){
        return problemService.updateProblemByPid(problem);
    }


    //pos表示的是相对于第一条记录的偏移，数据库中第一条记录pos为0，
    @GetMapping("/show")
    public List<ProblemFront> show(@RequestParam("pos") Integer pos, @RequestParam("limit") Integer limit){
        List<Problem> list = null;
        try {
            list = problemService.getProblemsPageIncludePrivate(pos, limit);
        } catch (Exception e){
            log.error("show problems list error: " + e.toString());
            return null;
        }
        return WebEntityToFrontEntity.ProblemToProblemFront(list);
    }

    @GetMapping("/cnt")
    public Integer getProblems(){
        return problemService.getProblemCntIncludePrivate();
    }

//    public static void main(String[] args) throws IOException {
//        String base = "/Users/bytedance/yly/zzuoj/deer-executor/";
//        String filePath = "data/problems/test";
//        File file = new File(base+filePath);
//        if(!file.exists()){
//            file.mkdir();
//        }
//        AAA aaa  = new AAA("123123","4",11,0, new AAA.B("aaa","ccc"));
//        System.out.println(aaa);
//        String temp = JSONObject.toJSONString(aaa);
//        File file1 =  new File(base+filePath+"/problem.json");
//        if(!file1.exists()){
//            file1.createNewFile();
//        }
//        BufferedWriter out = new BufferedWriter(new FileWriter(base+filePath+"/problem.json"));
//        out.write(temp);
//        out.close();
//
//    }
//
//    @Accessors(chain = true)
//    @AllArgsConstructor
//    @NoArgsConstructor
//    @Data
//    static
//    class AAA {
//
//
//        @JSONField(name = "AA")
//        String A ;
//        String b;
//        @JSONField(name = "BB")
//        Integer C ;
//        @JSONField(name = "QWE")
//        Integer d;
//
//        B e;
//
//        @Accessors(chain = true)
//        @AllArgsConstructor
//        @NoArgsConstructor
//        @Data
//        static
//        class B {
//            String Temp1;
//            String Temp2;
//        }
//    }

}
