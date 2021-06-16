package cn.edu.zzu.oj.client;

import cn.edu.zzu.oj.api.JudgeInternalApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = JudgeClient.SERVICE_NAME)
public interface JudgeClient extends JudgeInternalApi {
}
