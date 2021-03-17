package cn.edu.zzu.oj.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
