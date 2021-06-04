package cn.edu.zzu.oj.client;


import cn.edu.zzu.oj.api.UserInternalApi;

//@FeignClient(value = UserInternalApi.SERVICE_NAME)
public interface UserClient extends UserInternalApi {

}
