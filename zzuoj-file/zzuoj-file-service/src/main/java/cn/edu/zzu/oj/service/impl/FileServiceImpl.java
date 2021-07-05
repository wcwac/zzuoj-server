/*
 * Copyright 2020-2021 the original author or authors.
 *
 * Licensed under the General Public License, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.gnu.org/licenses/gpl-3.0.en.html
 */

package cn.edu.zzu.oj.service.impl;


import cn.edu.zzu.oj.config.FileSystemProperties;
import cn.edu.zzu.oj.dto.BinaryFileUploadReqDTO;
import cn.edu.zzu.oj.dto.FileDTO;
import cn.edu.zzu.oj.dto.FileDownloadReqDTO;
import cn.edu.zzu.oj.dto.PlainFileDownloadDTO;
import cn.edu.zzu.oj.service.FileService;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.file.Paths;
import java.util.*;

@Slf4j
@Service
@EnableConfigurationProperties(FileSystemProperties.class)
public class FileServiceImpl implements cn.edu.zzu.oj.service.FileService {

    @Autowired
    private FileSystemProperties fileSystemProperties;

    @Transactional
    @Override
    public FileDTO upload(MultipartFile file) {
//        // 计算文件 md5
//        String md5;
//        try {
//            md5 = CodecUtils.md5(file.getInputStream());
//        } catch (Exception e) {
//            throw new ApiException(ApiExceptionEnum.FILE_WRITE_ERROR);
//        }
//        // 查询是否已有相同 MD5
//        FileDO fileDO = fileDao.lambdaQuery().eq(FileDO::getMd5, md5).one();
//        if (fileDO != null) {
//            return fileConverter.to(fileDO);
//        }
//
//        Long id = snowflakeIdWorker.nextId();
//        fileDO = FileDO.builder()
//                .id(id)
//                .name(file.getOriginalFilename())
//                .extensionName(Files.getFileExtension(file.getOriginalFilename()))
//                .md5(md5)
//                .size(file.getSize())
//                .build();
//        AssertUtils.isTrue(fileDao.save(fileDO), ApiExceptionEnum.SERVER_BUSY);
//        try {
//            File writeFile = new File(Paths.get(fileSystemProperties.getBaseDir(), id.toString()).toString());
//            byte[] bytes = file.getBytes();
//            FileUtils.writeByteArrayToFile(writeFile, bytes);
//        } catch (Exception e) {
//            throw new ApiException(ApiExceptionEnum.FILE_WRITE_ERROR);
//        }
//        return fileConverter.to(fileDO);
        return null;
    }

    @Override
    public boolean deleteFile(String path) {
        File file = new File(String.valueOf(Paths.get(fileSystemProperties.getBaseDir(), path)));
        if( file.isDirectory() ){
            return deleteDir(file);
        }
        return file.delete();
    }

    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

    @Override
    @Transactional
    public List<FileDTO> uploadFiles(MultipartFile[] files, String userId) throws Exception {
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
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return uploadBinaryFiles(reqDTOList, userId);
    }

//    @Override
//    public void downloadFilesInZip(List<FileDownloadReqDTO> fileDownloadReqDTOList, ZipOutputStream zipOut) {
//        for (FileDownloadReqDTO fileDownloadReqDTO : fileDownloadReqDTOList) {
//            FileSystemResource file = new FileSystemResource(Paths.get(fileSystemProperties.getBaseDir(), fileDownloadReqDTO.getId().toString()).toString());
//            AssertUtils.isTrue(file.exists(), ApiExceptionEnum.FILE_NOT_EXISTS);
//            fileDownloadReqDTO.setFileSystemResource(file);
//        }
//        try {
//            for (FileDownloadReqDTO fileDownloadReqDTO : fileDownloadReqDTOList) {
//                FileSystemResource file = fileDownloadReqDTO.getFileSystemResource();
//                ZipEntry zipEntry = new ZipEntry(fileDownloadReqDTO.getDownloadFilename());
//                zipEntry.setSize(file.contentLength());
//                zipOut.putNextEntry(zipEntry);
//                StreamUtils.copy(file.getInputStream(), zipOut);
//                zipOut.closeEntry();
//            }
//            zipOut.finish();
//            zipOut.close();
//        } catch (Exception e) {
//            throw new ApiException(ApiExceptionEnum.FILE_READ_ERROR);
//        }
//    }
//
//    @Override
//    public byte[] downloadFilesInZipBytes(List<FileDownloadReqDTO> fileDownloadReqDTOList) {
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
//
//        downloadFilesInZip(fileDownloadReqDTOList, zipOutputStream);
//
//        return byteArrayOutputStream.toByteArray();
//    }
//


    @Override
    @Transactional
    public List<FileDTO> uploadBinaryFiles(List<BinaryFileUploadReqDTO> reqDTOList, String userId) throws Exception {

        int i=0;
        try {
            // 逐个写入文件系统
            for ( ; i < reqDTOList.size(); i++) {
                File writeFile = new File(Paths.get(fileSystemProperties.getBaseDir(), reqDTOList.get(i).getFilename()).toString());
                byte[] bytes = reqDTOList.get(i).getBytes();
                FileUtils.writeByteArrayToFile(writeFile, bytes);
            }
        } catch (Exception e) {
            // 失败，尝试逐个回滚删除，随后抛异常使数据库回滚
            for (int j = 0; j <= i; j++) {
                File file = new File(Paths.get(fileSystemProperties.getBaseDir(), reqDTOList.get(i).getFilename()).toString());
                if (file.exists()) {
                    file.delete();
                }
            }
            throw new Exception(e.getMessage());
        }

        // 构造返回值
        List<FileDTO> fileDTOList = new ArrayList<>();
        for (i = 0; i < reqDTOList.size(); i++) {
            FileDTO fileDTO = FileDTO.builder()
//                    .id(snowflakeIdWorker.nextId())
                    .name(reqDTOList.get(i).getFilename())
                    .extensionName(Files.getFileExtension(reqDTOList.get(i).getFilename()))
                    .size(reqDTOList.get(i).getSize())
//                    .userId(userId)
                    .gmtCreate(new Date())
                    .gmtModified(new Date())
                    .build();
        }
        return fileDTOList;
    }

    //    @Override
//    public List<PlainFileDownloadDTO> plainFileDownload(Long sizeLimit, List<PlainFileDownloadDTO> reqDTOList) {
//
//        for (PlainFileDownloadDTO plainFileDownloadDTO : reqDTOList) {
//            FileSystemResource file = new FileSystemResource(Paths.get(fileSystemProperties.getBaseDir(), plainFileDownloadDTO.getFileId().toString()).toString());
//            AssertUtils.isTrue(file.exists(), ApiExceptionEnum.FILE_NOT_EXISTS);
//            try {
//                AssertUtils.isTrue(file.contentLength() <= sizeLimit, ApiExceptionEnum.FILE_TOO_LARGE);
//                plainFileDownloadDTO.setBytes(StreamUtils.copyToByteArray(file.getInputStream()));
//            } catch (IOException e) {
//                throw new ApiException(ApiExceptionEnum.FILE_READ_ERROR);
//            }
//        }
//        return reqDTOList;
//    }
//
//    @Override
//    public FileDTO queryByMd5(String md5) {
//        FileDO fileDO = fileDao.lambdaQuery().eq(FileDO::getMd5, md5).one();
//        return fileConverter.to(fileDO);
//    }
//
//    @Override
//    public byte[] downloadFile(long id) throws IOException {
//        FileSystemResource file = new FileSystemResource(Paths.get(fileSystemProperties.getBaseDir(), String.valueOf(id)).toString());
//        AssertUtils.isTrue(file.exists(), ApiExceptionEnum.FILE_NOT_EXISTS);
//        return StreamUtils.copyToByteArray(file.getInputStream());
//    }
//
//    @Override
//    public void downloadToStream(long fileId, OutputStream outputStream) throws IOException {
//        FileSystemResource file = new FileSystemResource(Paths.get(fileSystemProperties.getBaseDir(), String.valueOf(fileId)).toString());
//        StreamUtils.copy(file.getInputStream(), outputStream);
//    }
}
