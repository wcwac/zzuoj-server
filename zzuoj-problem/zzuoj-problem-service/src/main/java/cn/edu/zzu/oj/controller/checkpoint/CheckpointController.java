package cn.edu.zzu.oj.controller.checkpoint;


import cn.edu.zzu.oj.anotation.BaseResponse;
import cn.edu.zzu.oj.anotation.UserSession;
import cn.edu.zzu.oj.client.FileClient;
import cn.edu.zzu.oj.dto.BinaryFileUploadReqDTO;
import cn.edu.zzu.oj.entity.Checkpoint;
import cn.edu.zzu.oj.entity.jwt.UserSessionDTO;
import cn.edu.zzu.oj.service.impl.CheckpointServiceImpl;
import org.hibernate.validator.constraints.pl.REGON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
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
    @PostMapping("/uploadCheckPoints")
    public String upload(@RequestParam("files")  MultipartFile[] files, @UserSession UserSessionDTO userSessionDTO) throws Exception {
        List<BinaryFileUploadReqDTO> reqDTOList = new ArrayList<>(files.length);
        try {
            for (MultipartFile file : files) {
                byte[] bytes = file.getBytes();
                reqDTOList.add(
                        BinaryFileUploadReqDTO.builder()
                                .filename(file.getOriginalFilename())
                                .bytes(bytes)
                                .size((long) bytes.length)
                                .inputStream(file.getInputStream())
                                .build()
                );
            }

            List<Checkpoint> checkpoints = new ArrayList<>();
            checkpointService.saveBatch(checkpoints);


            fileClient.uploadBinaryFiles(reqDTOList, userSessionDTO.getUserId());
        } catch (Exception e) {
            log.error("uploadCheckPoints fail: " + e.getMessage());
            throw new Exception(e.getMessage());
        }
        return "添加成功";
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
    public String deleteByCid(@RequestParam("cId") Long cId) throws Exception {
        try {
            checkpointService.deleteCheckPointById(cId);
        } catch (Exception e){
            log.error("delete check point by  cId fail: " + e.getMessage());
            throw new Exception(e.getMessage());
        }
        return "delete check point by cId fail";
    }
}
