package cn.edu.zzu.oj.client;

import cn.edu.zzu.oj.api.FilesysApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient( value = FilesysApi.SERVICE_NAME)
public interface FileClient extends FilesysApi {

}
