package cn.edu.zzu.oj.client;

import cn.edu.zzu.oj.api.AuthInternalApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = AuthInternalApi.SERVICE_NAME)
public interface AuthClient extends AuthInternalApi {

}
