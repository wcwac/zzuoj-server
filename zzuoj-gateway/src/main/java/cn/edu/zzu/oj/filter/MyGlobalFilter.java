package cn.edu.zzu.oj.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


//会先执行全局过滤器，然后执行网关过滤器 
//自定义全局过滤器 做权限验证
@Component //声明，会对所有请求有效
public class MyGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange);
    }


    //数值越小优先级越高
    @Override
    public int getOrder() {
        return 0;
    }
}
