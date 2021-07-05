package cn.edu.zzu.oj.controller.checkpoint;


import cn.edu.zzu.oj.anotation.BaseResponse;
import cn.edu.zzu.oj.anotation.UserSession;
import cn.edu.zzu.oj.client.FileClient;
import cn.edu.zzu.oj.dto.BinaryFileUploadReqDTO;
import cn.edu.zzu.oj.entity.Checkpoint;
import cn.edu.zzu.oj.entity.checkpoint.CheckPointConf;
import cn.edu.zzu.oj.entity.checkpoint.TestCaseDTO;
import cn.edu.zzu.oj.entity.jwt.UserSessionDTO;
import cn.edu.zzu.oj.service.impl.CheckpointServiceImpl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.awt.peer.CheckboxPeer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yuliuyuan
 * @since 2021-05-18
 */


@BaseResponse
@RestController
@RequestMapping("/admin/checkpoint")
public class CheckpointController {
    Logger log = LoggerFactory.getLogger(CheckpointController.class);

    @Autowired
    FileClient fileClient;

    @Autowired
    CheckpointServiceImpl checkpointService;

    //批量添加测试点
    //上传的是已经排好序的文件，挨着的两个一个是in
    @PostMapping("/uploadCheckPoints")
    public String upload(@RequestParam("files")  MultipartFile[] files,@RequestParam String obj,  @UserSession UserSessionDTO userSessionDTO) throws Exception {
        List<BinaryFileUploadReqDTO> reqDTOList = new ArrayList<>(files.length);
        try {
            JSONObject jsonObject = JSON.parseObject(obj);

            for (MultipartFile file : files) {
                byte[] bytes = file.getBytes();
                reqDTOList.add(
                        BinaryFileUploadReqDTO.builder()
                                .filename( file.getOriginalFilename())
                                .bytes(bytes)
                                .size((long) bytes.length)
                                .inputStream(file.getInputStream())
                                .build()
                );
            }

            List<Checkpoint> checkpoints = new ArrayList<>();
            for(int i=0;i<reqDTOList.size(); i+=2){
                Checkpoint temp = new Checkpoint().setProblemId(Integer.parseInt((String) jsonObject.get("pId")))
                        .setUserId( userSessionDTO.getUserId() )
                        .setIsPrivate( "Y" )
                        .setCreateTime(new Date())
                        .setModifyTime(new Date())
                        .setInputSize( reqDTOList.get(i).getSize() )
                        .setOutputSize( reqDTOList.get(i+1).getSize() )
                        .setInputName( reqDTOList.get(i).getFilename() )
                        .setOutputName( reqDTOList.get(i+1).getFilename() );
                checkpoints.add(temp);
            }
            checkpointService.saveBatch(checkpoints);

            for(int i=0; i<reqDTOList.size(); i++){
                String temp = reqDTOList.get(i).getFilename();
                reqDTOList.get(i).setFilename(  (String) jsonObject.get("pId") + "/" + temp);
            }
            fileClient.uploadBinaryFiles(reqDTOList, userSessionDTO.getUserId());


            Integer problemId = Integer.parseInt((String) jsonObject.get("pId")) ;
            //上传成功之后，修改problem.json文件
            String base = "/Users/bytedance/yly/zzuoj/deer-executor/";
            String filePath = "data/problems/"+problemId;

            //读取
            BufferedReader reader = new BufferedReader(new FileReader(base + filePath + "/problem.json"));
            StringBuilder stringBuilder = new StringBuilder();
            char[] buffer = new char[10];
            while (reader.read(buffer) != -1) {
                stringBuilder.append(new String(buffer));
                buffer = new char[10];
            }
            reader.close();
            String content = stringBuilder.toString();

            CheckPointConf checkPointConf= (CheckPointConf) JSONObject.parseObject(content, CheckPointConf.class);

            int p = 0;
            List<TestCaseDTO> testCaseDTOS = checkPointConf.getTestCaseList();
            if(testCaseDTOS == null){
                testCaseDTOS = new ArrayList<>();
            }else {
                p = checkPointConf.getTestCaseList().size();
            }

            for(int i=0; i<reqDTOList.size(); i+=2){
                p++;
                TestCaseDTO testCaseDTO = new TestCaseDTO()
                        .setHandle( String.valueOf(p))
                        .setEnabled(true)
                        .setInput(reqDTOList.get(i).getFilename().split("/")[1])
                        .setOutput(reqDTOList.get(i+1).getFilename().split("/")[1])
                        .setName(problemId + " Test #"+p);
                testCaseDTOS.add(testCaseDTO);
            }

            checkPointConf.setTestCaseList(testCaseDTOS);

            //写回problem.json
            BufferedWriter out = new BufferedWriter(new FileWriter(base + filePath + "/problem.json"));
            out.write( JSONObject.toJSONString(checkPointConf) );
            out.close();

        } catch (Exception e) {
            log.error("uploadCheckPoints fail: " + e.getMessage());
            throw new Exception(e.getMessage());
        }
        return "添加成功";
    }


    //根据题目Id获取获取所有测试点
    @GetMapping("/getCheckPointByPid")
    public List<Checkpoint> getCheckPointByPid(@RequestParam("pId") Integer pId) throws Exception {
        List<Checkpoint> checkpoints = null;
        try {
            checkpoints = checkpointService.queryByPid(pId);
        } catch (Exception e) {
            log.error("get checkpoint by problem id fail: " + e.getMessage());
            throw new Exception(e.getMessage());
        }
        return checkpoints;
    }

    //删除所有测试点，删除题目时使用
    @GetMapping("/deleteCheckPointsByPid")
    public String deleteByPid(@RequestParam("pId")  Integer pId) throws Exception {
        try {
            checkpointService.deleteAllPointsByPid(pId);
        } catch (Exception e) {
            log.error("delete check point by problem id fail: " + e.getMessage());
            throw new Exception(e.getMessage());
        }
        return "delete check point by problem id fail";
    }

    //删除单个测试点
    @GetMapping("/deleteCheckPointsByCid")
    public String deleteByCid(@RequestParam("pId") Integer pId, @RequestParam("cId") Long cId) throws Exception {
        try {
            checkpointService.deleteCheckPointById(cId);
            //todo: 删除文件
            fileClient.deleteFiles(pId + "/" + cId);
        } catch (Exception e){
            log.error("delete check point by  cId fail: " + e.getMessage());
            throw new Exception(e.getMessage());
        }
        return "delete check point by cId fail";
    }
}
