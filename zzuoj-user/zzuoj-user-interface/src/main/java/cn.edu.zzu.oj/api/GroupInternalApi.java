package cn.edu.zzu.oj.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping("/internal/group")
public interface GroupInternalApi {
    String SERVICE_NAME = "zzuoj-user";

}
