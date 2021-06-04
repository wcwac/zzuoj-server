package cn.edu.zzu.oj.api;

import cn.edu.zzu.oj.entity.SourceT;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@RequestMapping("/admin/judge/internal")
public interface JudgeInternalApi {
    String SERVICE_NAME = "ZZUOJ-JUDGE";

    @PostMapping("/judge")
    public Integer judge(@RequestBody SourceT sourceCode) throws IOException, InterruptedException;
}
