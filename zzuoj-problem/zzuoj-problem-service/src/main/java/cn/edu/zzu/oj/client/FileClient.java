package cn.edu.zzu.oj.client;

import cn.edu.zzu.oj.api.FilesysApi;
import cn.edu.zzu.oj.dto.BinaryFileUploadReqDTO;
import cn.edu.zzu.oj.dto.FileDTO;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

@FeignClient( value = FilesysApi.SERVICE_NAME)
public class FileClient implements FilesysApi {
    @Override
    public List<FileDTO> uploadBinaryFiles(List<BinaryFileUploadReqDTO> reqDTOList, String userId) throws Exception {
        return null;
    }
}
