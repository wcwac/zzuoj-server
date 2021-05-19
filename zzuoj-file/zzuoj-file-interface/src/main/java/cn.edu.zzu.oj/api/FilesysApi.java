/*
 * Copyright 2020-2021 the original author or authors.
 *
 * Licensed under the General Public License, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.gnu.org/licenses/gpl-3.0.en.html
 */

package cn.edu.zzu.oj.api;

import cn.edu.zzu.oj.dto.BinaryFileUploadReqDTO;
import cn.edu.zzu.oj.dto.FileDTO;
import cn.edu.zzu.oj.dto.FileDownloadReqDTO;
import cn.edu.zzu.oj.dto.PlainFileDownloadDTO;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequestMapping("/internal/file")
public interface FilesysApi {
    String SERVICE_NAME = "zzuoj-file";
//
    @PostMapping(value = "/uploadBinaryFiles", consumes = "application/json")
    List<FileDTO> uploadBinaryFiles(@RequestBody List<BinaryFileUploadReqDTO> reqDTOList,
                                    @RequestParam("userId") String userId) throws Exception;

//    @GetMapping(value = "/download", headers = "content-type=application/json")
//    Resource download(@RequestParam("id") long id) throws IOException;
//
//    @PostMapping(value = "/zipDownload", headers = "content-type=application/json")
//    Resource download(@RequestBody List<FileDownloadReqDTO> reqDTOList);
//
//    @PostMapping(value = "/plainFileDownload", headers = "content-type=application/json")
//    List<PlainFileDownloadDTO> plainFileDownload(@RequestParam("sizeLimit") Long sizeLimit,
//                                                 @RequestBody List<PlainFileDownloadDTO> reqDTOList); // 限制下载文件的大小
}