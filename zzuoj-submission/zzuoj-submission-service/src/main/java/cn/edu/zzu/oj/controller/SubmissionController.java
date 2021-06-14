package cn.edu.zzu.oj.controller;

import cn.edu.zzu.oj.Exceptions.BaseException;
import cn.edu.zzu.oj.anotation.BaseResponse;
import cn.edu.zzu.oj.client.JudgeClient;
import cn.edu.zzu.oj.converter.Converter;
import cn.edu.zzu.oj.converter.EntityToFrontEntity;
import cn.edu.zzu.oj.entity.Solution;
import cn.edu.zzu.oj.entity.SourceCode;
import cn.edu.zzu.oj.entity.SourceT;
import cn.edu.zzu.oj.entity.frontToWeb.SolutionFront;
import cn.edu.zzu.oj.enums.HttpStatus;
import cn.edu.zzu.oj.service.impl.SolutionServiceImpl;
import cn.edu.zzu.oj.service.impl.SourceCodeServiceImpl;
import cn.edu.zzu.oj.util.LanguageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@BaseResponse
@RestController
@RequestMapping("/submission")
public class SubmissionController {
    private static Logger log = LoggerFactory.getLogger(SubmissionController.class);

    @Autowired
    JudgeClient judgeClient;

    //提交的代码
    @Autowired
    SourceCodeServiceImpl sourceCodeService;

    //编译执行后的结果
    @Autowired
    SolutionServiceImpl solutionService;

    //todo: 还得加上一个文件上传的接口
    @PostMapping("/submit")
    public String submit(@RequestBody SourceCode sourceCode){
        sourceCode.setCodeLength(sourceCode.getCode().getBytes().length);
        System.out.println(sourceCode);

        String base = "/Users/bytedance/yly/zzuoj/deer-executor/";
        String filePath = "data/codes/";
        try {
            File file1 = new File(base+filePath+sourceCode.getUserId());
            System.out.println(file1.getPath());
            if(!file1.exists()){
                file1.mkdir();
            }
            File file2 = new File(base+filePath+sourceCode.getUserId()+"/"+sourceCode.getProblemId()+ LanguageUtil.getFileSuf(sourceCode.getLanguage()));
            if(!file2.exists()){
                file2.createNewFile();
            }

            filePath = filePath + sourceCode.getUserId()+"/"+sourceCode.getProblemId()+ LanguageUtil.getFileSuf(sourceCode.getLanguage());
            BufferedWriter out = new BufferedWriter(new FileWriter(base+filePath));
            out.write(sourceCode.getCode());
            out.close();
            System.out.println("文件创建成功！");
        } catch (IOException e) {
            throw new BaseException(HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
        }

        try{
            int id = sourceCodeService.insert(sourceCode);
            sourceCode.setSolutionId(id);
            //还得查一下soluion_id，逻辑离谱，赶紧弄，先不管了

            SourceT sourceT= Converter.SourceCodeToSourceT(sourceCode, filePath);

            judgeClient.judge(sourceT);
        } catch (Exception e){
            throw new BaseException(HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
        }
        return "submit success";
    }

    //uid空设成"", pid空设成0
    @GetMapping("/show")
    public List<SolutionFront> show(@RequestParam("pos") Integer pos, @RequestParam("limit") Integer limit, @RequestParam(value = "uid", required = false) String  uid, @RequestParam(value = "pid", required = false) Integer  pid){
        List<Solution> list = null;
        try {
            if(uid == null){
                if(pid == null){
                    list = solutionService.getSolutionsPage(pos, limit);
                } else {
                    list = solutionService.getSolutionsPageByPid(pos, limit, pid);
                }
            }else{
                if(pid == null){
                    list = solutionService.getSolutionsPageByUid(pos, limit, uid);
                } else {
                    list = solutionService.getSolutionsPageByUidPid(pos, limit, uid, pid);
                }
            }
        } catch (Exception e){
            log.error("show solutions list error: " + e.toString());
            return null;
        }
        return EntityToFrontEntity.SolutionToSolutionFront(list);
    }

    //uid空设成"", pid空设成0
    @GetMapping("/cnt")
    public Integer getSolutionCnt(@RequestParam(value = "uid", required = false) String uid, @RequestParam(value = "pid", required = false) Integer pid){
        Integer res = 0;
        if(uid == null){
            if(pid == null){
                res = solutionService.getSolutionCnt();
            } else {
                res = solutionService.getSolutionCntByPid(pid);
            }
        }else{
            if(pid == null){
                res = solutionService.getSolutionCntByUid(uid);
            } else {
                res = solutionService.getSolutionCntByUidPid(uid, pid);
            }
        }
        return solutionService.getSolutionCnt();
    }
}