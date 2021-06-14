package cn.edu.zzu.oj.client;


import cn.edu.zzu.oj.ProblemInternalApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = ProblemInternalApi.SERVICE_NAME)
public interface ProblemClient extends ProblemInternalApi {

}