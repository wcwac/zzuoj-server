package cn.edu.zzu.oj.client;

import cn.edu.zzu.oj.SubmissionApi;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(value = SubmissionApi.SERVICE_NAME)
public interface SubmissionClient extends SubmissionApi {
}
