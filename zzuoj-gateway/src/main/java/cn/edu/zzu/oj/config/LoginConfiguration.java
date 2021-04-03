package cn.edu.zzu.oj.config;

import cn.edu.zzu.oj.filter.LoginFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoginConfiguration {

    public RouteLocator routeLocator(RouteLocatorBuilder builder){
        return builder.routes().route(r->r
        //判断条件
        .path("/login/**")
        //路由到微服务
        .uri("lb://zzuoj-user")
        //注册自定义网关过滤器
        .filters( new LoginFilter())
        //路由id 唯一
        .id("zzuoj-user"))
        .build();
    }
}
