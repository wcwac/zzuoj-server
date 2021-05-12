package cn.edu.zzu.oj;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//SpringCloud Gateway会自动完成负载均衡。
@SpringBootApplication(scanBasePackages = {"cn.edu.zzu.oj"})
@EnableFeignClients
@EnableEurekaClient
public class GateWayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication.class,args);
    }
}