package cn.edu.zzu.oj.controller;


import cn.edu.zzu.oj.anotation.BaseResponse;
import cn.edu.zzu.oj.anotation.UserSession;
import cn.edu.zzu.oj.dto.FileDTO;
import cn.edu.zzu.oj.entity.jwt.UserSessionDTO;
import cn.edu.zzu.oj.service.FileService;
import cn.edu.zzu.oj.service.impl.FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/admin/file")
@BaseResponse
public class FileController {

    @Autowired
    private FileServiceImpl fileService;

//    @PostMapping(value = "/upload", headers = "content-type=multipart/form-data")
//    @ApiResponseBody
//    public FileDTO upload(@RequestParam("file") @NotNull MultipartFile file) {
//        return fileService.upload(file);
//    }

    @PostMapping("/uploadFiles")
    public List<FileDTO> upload(@RequestParam("files")  MultipartFile[] files, @UserSession UserSessionDTO userSessionDTO) throws Exception {
        return fileService.uploadFiles(files, userSessionDTO.getUserId());
    }


//    @PostMapping(value = "/zipDownload")
//    public void zipDownload(@RequestBody List<FileDownloadReqDTO> fileDownloadReqDTOList,
//                            HttpServletResponse response) throws IOException {
//        log.warn("zipDownload: {}", fileDownloadReqDTOList);
//        response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment"); // 前端定义文件名
//        response.setContentType("application/zip; charset=utf-8");
//        response.setCharacterEncoding("UTF-8");
//        response.setStatus(HttpServletResponse.SC_OK);
//        fileService.downloadFilesInZip(fileDownloadReqDTOList, new ZipOutputStream(response.getOutputStream()));
//    }
//
//    /**
//     * @Description 以 filename 为文件名下载指定 fileId 的文件
//     **/
//    @GetMapping(value = "/download/{fileId}/{filename}")
//    public void download(@PathVariable("fileId") long fileId,
//                         @PathVariable("filename") String filename,
//                         HttpServletResponse response) throws IOException {
//        log.info("download: {}", fileId);
//
//        // 从 filename 获取 contentType
//        String contentType = null;
//        try {
//            contentType = Files.probeContentType(Paths.get(filename)); // 这个 API 最终调用 JNI, 结果可能因 OS 而不同
//        } catch (Exception e) {
//            log.warn("", e);
//        }
//
//        // 设置 header
//        if (contentType == null) {
//            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
//            response.setContentType("application/octet-stream; charset=utf-8");
//        } else {
//            response.setContentType(contentType);
//        }
//        response.setCharacterEncoding("UTF-8");
//        response.setStatus(HttpServletResponse.SC_OK);
//
//        // 读取文件
//        try {
//            fileService.downloadToStream(fileId, response.getOutputStream());
//        } catch (Exception e) {
//            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//        }
//    }
//
//    @GetMapping(value = "/queryByMd5")
//    public FileDTO queryByMd5(@RequestParam("md5") String md5) {
//        return fileService.queryByMd5(md5);
//    }
}
