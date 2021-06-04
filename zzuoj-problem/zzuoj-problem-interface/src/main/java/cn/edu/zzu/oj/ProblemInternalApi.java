package cn.edu.zzu.oj;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/internal/problem")
public interface ProblemInternalApi {
    String SERVICE_NAME = "ZZUOJ-PROBLEM";

    //增加一次提交数
    @GetMapping("/increSubmitAc")
    Integer increSubmitAc(@RequestParam("problemId")Integer problemId,@RequestParam("ac") boolean ac);
}
