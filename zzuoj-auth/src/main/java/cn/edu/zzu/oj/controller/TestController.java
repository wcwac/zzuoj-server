package cn.edu.zzu.oj.controller;


import cn.edu.zzu.oj.Exceptions.BaseException;
import cn.edu.zzu.oj.anotation.BaseResponse;
import cn.edu.zzu.oj.enums.HttpStatus;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@BaseResponse
@RestController
@RequestMapping("/auth")
public class TestController {
    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${eureka.client.service-url.defaultZone}")
    private String eurekaServer;

    @Value("${server.port}")
    private String port;

    @GetMapping("/show")
    public String show(){
        return "applicationName: "+applicationName+"\n"+
                "eurekaServer: "+eurekaServer+"\n"+
                "port: "+port;
    }

    @HystrixCommand(fallbackMethod = "hello1")
    @GetMapping("/test/{id}")
    public String hello(@PathVariable("id") String name){
        if(!name.equals("salix")){
            throw new RuntimeException();
        }
        return "hello "+name;
    }

    public String hello01(@PathVariable("id") String name){
        return "hello error";
    }


    @GetMapping("/test/show")
    public String show1(){
//        throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR);
        return "hello salix";
    }
}
