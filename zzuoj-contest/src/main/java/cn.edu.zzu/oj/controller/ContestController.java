package cn.edu.zzu.oj.controller;
import cn.edu.zzu.oj.anotation.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yuliuyuan
 * @since 2021-03-31
 */

@BaseResponse
@RestController
@RequestMapping("/contest")
public class ContestController {
    private static Logger log = LoggerFactory.getLogger(ContestController.class);

    @GetMapping("/test")
    public String test(){
        return "hello contest";
    }
}
