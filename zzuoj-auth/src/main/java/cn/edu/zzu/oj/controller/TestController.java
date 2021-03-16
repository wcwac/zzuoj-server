package cn.edu.zzu.oj.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/test")
    public String hello(){
        return "hello salix";
    }
}
