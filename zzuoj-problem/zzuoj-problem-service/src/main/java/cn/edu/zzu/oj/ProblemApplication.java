package cn.edu.zzu.oj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@MapperScan("cn.edu.zzu.oj.mapper")
@SpringBootApplication(scanBasePackages = {"cn.edu.zzu.oj.client"})
@EnableEurekaClient
public class ProblemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProblemApplication.class, args);
    }
}
