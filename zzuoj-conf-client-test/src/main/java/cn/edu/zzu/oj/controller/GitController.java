package cn.edu.zzu.oj.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GitController {

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
}