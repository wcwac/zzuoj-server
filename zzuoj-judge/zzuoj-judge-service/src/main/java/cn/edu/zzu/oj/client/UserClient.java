package cn.edu.zzu.oj.client;


import cn.edu.zzu.oj.api.UserInternalApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = UserInternalApi.SERVICE_NAME)
public interface UserClient extends UserInternalApi {

}
