package cn.edu.zzu.oj.client;

import cn.edu.zzu.oj.api.FilesysApi;
import cn.edu.zzu.oj.dto.BinaryFileUploadReqDTO;
import cn.edu.zzu.oj.dto.FileDTO;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

@FeignClient( value = FilesysApi.SERVICE_NAME)
public interface FileClient extends FilesysApi {

}
