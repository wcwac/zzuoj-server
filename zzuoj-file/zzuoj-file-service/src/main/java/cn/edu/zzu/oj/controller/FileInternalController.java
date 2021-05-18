package cn.edu.zzu.oj.controller;

import cn.edu.zzu.oj.anotation.BaseResponse;
import cn.edu.zzu.oj.api.FilesysApi;
import cn.edu.zzu.oj.dto.BinaryFileUploadReqDTO;
import cn.edu.zzu.oj.dto.FileDTO;
import cn.edu.zzu.oj.dto.FileDownloadReqDTO;
import cn.edu.zzu.oj.dto.PlainFileDownloadDTO;
import cn.edu.zzu.oj.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
public class FileInternalController implements FilesysApi {

    @Autowired
    private FileService fileService;

//    @Override
//    public List<FileDTO> uploadBinaryFiles(List<BinaryFileUploadReqDTO> reqDTOList, long userId) {
//        return fileService.uploadBinaryFiles(reqDTOList, userId);
//    }
//
//    @Override
//    public Resource download(long id) throws IOException {
//        return new ByteArrayResource(fileService.downloadFile(id));
//    }
//
//    @Override
//    public Resource download(List<FileDownloadReqDTO> reqDTOList) {
//        return new ByteArrayResource(fileService.downloadFilesInZipBytes(reqDTOList));
//    }
//
//    @Override
//    public List<PlainFileDownloadDTO> plainFileDownload(Long sizeLimit, List<PlainFileDownloadDTO> reqDTOList) {
//        return fileService.plainFileDownload(sizeLimit, reqDTOList);
//    }
}

