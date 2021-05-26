package cn.edu.zzu.oj.controller.checkpoint;


import cn.edu.zzu.oj.anotation.BaseResponse;
import cn.edu.zzu.oj.anotation.UserSession;
import cn.edu.zzu.oj.client.FileClient;
import cn.edu.zzu.oj.dto.BinaryFileUploadReqDTO;
import cn.edu.zzu.oj.entity.Checkpoint;
import cn.edu.zzu.oj.entity.jwt.UserSessionDTO;
import cn.edu.zzu.oj.service.impl.CheckpointServiceImpl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.tools.javac.comp.Check;
import org.omg.PortableInterceptor.INACTIVE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.awt.peer.CheckboxPeer;
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
