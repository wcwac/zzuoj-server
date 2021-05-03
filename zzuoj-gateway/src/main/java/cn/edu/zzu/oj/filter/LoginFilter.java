package cn.edu.zzu.oj.filter;

import cn.edu.zzu.oj.config.FilterProperties;
import cn.edu.zzu.oj.entity.UserSessionDTO;
import cn.edu.zzu.oj.util.JWTUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Optional;


//自定义网关过滤器
@Component
@EnableConfigurationProperties({FilterProperties.class})
public class LoginFilter implements GlobalFilter, Ordered  {
    private static Logger log = LoggerFactory.getLogger(LoginFilter.class);

    @Autowired
    private FilterProperties filterProp;

    private ObjectMapper objectMapper;

    public LoginFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

//    @Autowired
//    private

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //三种url
        // 1. 无需登陆即可访问的url(filterProp里定义的)
        // 2. 普通用户需要登陆之后可以访问的
        // 3. 普通管理员可以访问的的url形式: http://ip:port/微服务名称/admin/***
        // 4. root管理员可以访问的url形式： http://ip:port/微服务名称/root/***

        //继续向下执行
        ServerHttpRequest request = exchange.getRequest();
        String requestUrl = request.getPath().toString();
        String realIp = Optional.of(request)
                .map(o -> o.getHeaders().getFirst("x-real-ip"))
                .orElse(String.valueOf(request.getRemoteAddress()));

        log.info("Filter From: {}\tUrl: {}\tParams: {}", realIp, requestUrl, request.getQueryParams());
        //解密
        //获取token
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        ServerHttpResponse resp = exchange.getResponse();

        boolean isAllowPath = isAllowPath(requestUrl);
        if(isAllowPath) {
            return chain.filter(exchange);
        }
        if (token == null) {
            // 无 session，非 allowUrl
            return authErro(exchange, " 你的账号没有该权限或未登录! ");
        }
//        else if(isAllowPath){
//             allowUrl
//            return chain.filter(exchange);
//        }

        try {
            //有token
            JWTUtil.checkToken(token, objectMapper);
            return chain.filter(exchange);
//            return chain.filter(exchange).then(thenHandlerSession(exchange));
        } catch (ExpiredJwtException e) {
            log.error(e.getMessage(), e);
            if (e.getMessage().contains("Allowed clock skew")) {
                return authErro(exchange, "认证过期");
            } else {
                return authErro(exchange, "认证失败");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return authErro(exchange, "认证失败");
        }
    }

    private Mono<Void> thenHandlerSession(ServerWebExchange exchange) {
        return Mono.fromRunnable(() -> {
            List<String> userInfos = exchange.getResponse().getHeaders().remove(UserSessionDTO.HEADER_KEY);
            Optional.of(userInfos).filter(list -> !list.isEmpty()).map(list -> list.get(0)).ifPresent(userInfoStr -> {
                Optional.of(exchange).map(ServerWebExchange::getSession).map(Mono::block).ifPresent(webSession -> {
                    Map<String, Object> map = webSession.getAttributes();
                    if (UserSessionDTO.HEADER_VALUE_LOGOUT.equals(userInfoStr)) {
                        map.remove(UserSessionDTO.HEADER_KEY);
                    } else {
                        map.put(UserSessionDTO.HEADER_KEY, userInfoStr);
                    }
                });
            });
        });
    }


    @Override
    public int getOrder() {
        return 0;
    }


//    private Mono<Void> authErro(ServerHttpResponse resp, String mess) {
//        resp.setStatusCode(HttpStatus.UNAUTHORIZED);
//        resp.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
//        Resp<String> returnData = new Resp<>(org.apache.http.HttpStatus.SC_UNAUTHORIZED, mess, mess);
//        String returnStr = "";
//        try {
//            returnStr = objectMapper.writeValueAsString(returnData);
//        } catch (JsonProcessingException e) {
//            log.error(e.getMessage(), e);
//        }
//        DataBuffer buffer = resp.bufferFactory().wrap(returnStr.getBytes(StandardCharsets.UTF_8));
//        return resp.writeWith(Flux.just(buffer));
//    }
    private Mono<Void> authErro(ServerWebExchange exchange, String msg) {
//         返回鉴权失败的消息
        JSONObject message = new JSONObject();
        message.put("code", HttpStatus.UNAUTHORIZED.value());
        message.put("message", msg);
        message.put("timestamp", String.valueOf(System.currentTimeMillis()));
        message.put("data", null);
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
        return response.writeWith(Mono.just(response.bufferFactory().wrap(JSON.toJSONBytes(message))));
    }

    private boolean isAllowPath(String requestUrl) {
        System.out.println("111111:"+requestUrl);
        for (String allowPath : filterProp.getAllowPaths()){
            System.out.println("22222222:"+allowPath);
            if (requestUrl.startsWith(allowPath))
                return true;
        }
        return false;
    }
}
