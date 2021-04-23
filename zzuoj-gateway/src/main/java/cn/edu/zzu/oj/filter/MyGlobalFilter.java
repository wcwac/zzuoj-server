/*
 * Copyright 2021-2022 the original author or authors.
 *
 * Licensed under the General Public License, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.gnu.org/licenses/gpl-3.0.en.html
 */

package cn.edu.zzu.oj.filter;

import cn.edu.zzu.oj.entity.UserSessionDTO;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

import java.util.*;


//https://www.cnblogs.com/liukaifeng/p/10055862.html
//自定义全局过滤器 做权限验证、登陆
@Log4j2
@Component
//@EnableConfigurationProperties({FilterProperties.class})
public class MyGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public int getOrder() {
        return 0;
    }

//    @Autowired
//    private FilterProperties filterProp;
//
//    @Autowired
//    private UserClient userClient;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        ServerHttpRequest request = exchange.getRequest();
//        String requestUrl = request.getPath().toString();
//        String realIp = Optional.of(request)
//                .map(o -> o.getHeaders().getFirst("x-real-ip"))
//                .orElse(String.valueOf(request.getRemoteAddress()));
//        log.info("Filter From: {}\tUrl: {}\tParams: {}", realIp, requestUrl, request.getQueryParams());
//        // 取 token 并解密
//        UserSessionDTO userSessionDTO = Optional.of(exchange)
//                .map(ServerWebExchange::getSession)
//                .map(Mono::block)
//                .map(WebSession::getAttributes)
//                .map(map -> map.get(UserSessionDTO.HEADER_KEY))
//                .map(o -> (String) o)
//                .map(o -> JSON.parseObject(o, UserSessionDTO.class))
//                .orElse(null);
        // 无 session，非 allowUrl
//        boolean isAllowPath = isAllowPath(requestUrl);
//        if (userSessionDTO == null && !isAllowPath) {
//            return returnNoPermission(exchange, " 你的账号没有该权限或未登录! ");
//        }

        // 鉴权
//        if (userSessionDTO != null) {
//            List<String> urlRoles = NonExceptionOptional.ofNullable(() -> permissionClient.urlToRoles(requestUrl.replace("/api", "")))
//                    .orElse(Lists.newArrayList());
//            List<String> roles = NonExceptionOptional.ofNullable(() -> userClient.queryRolesById(userSessionDTO.getUserId()))
//                    .orElse(Lists.newArrayList());
//
//            if (!urlRoles.contains(PermissionEnum.ALL.name) && Collections.disjoint(roles, urlRoles) && !isAllowPath) {
//                log.warn("have not permission {} {}", userSessionDTO, requestUrl);
//                return returnNoPermission(exchange, String.format("This User has no permission on '%s'", requestUrl));
//            }

            // 装饰器 修改 getHeaders 方法
//            ServerHttpRequestDecorator decorator = new ServerHttpRequestDecorator(exchange.getRequest()) {
//                @Override
//                public HttpHeaders getHeaders() {
//                    MultiValueMap<String, String> multiValueMap = CollectionUtils.toMultiValueMap(new LinkedCaseInsensitiveMap(8, Locale.ENGLISH));
//                    super.getHeaders().forEach((key, value) -> multiValueMap.put(key, value));
//                    //              multiValueMap.remove("cookie"); // 在此处已解码 token, 故不下传省流量, 如果后续有多值 cookie 此处需要修改
//                    multiValueMap.remove(UserSessionDTO.HEADER_KEY);
//                    multiValueMap.add(UserSessionDTO.HEADER_KEY, JSON.toJSONString(userSessionDTO));
//                    return new HttpHeaders(multiValueMap);
//                }
//            };
//            return chain.filter(exchange.mutate().request(decorator).build()).then(thenHandlerSession(exchange));
//        }
//        return chain.filter(exchange).then(thenHandlerSession(exchange));
        return chain.filter(exchange);
    }

//    private Mono<Void> thenHandlerSession(ServerWebExchange exchange) {
//        return Mono.fromRunnable(() -> {
//            List<String> userInfos = exchange.getResponse().getHeaders().remove(UserSessionDTO.HEADER_KEY);
//            Optional.of(userInfos).filter(list -> !list.isEmpty()).map(list -> list.get(0)).ifPresent(userInfoStr -> {
//                Optional.of(exchange).map(ServerWebExchange::getSession).map(Mono::block).ifPresent(webSession -> {
//                    Map<String, Object> map = webSession.getAttributes();
//                    if (UserSessionDTO.HEADER_VALUE_LOGOUT.equals(userInfoStr)) {
//                        map.remove(UserSessionDTO.HEADER_KEY);
//                    } else {
//                        // 将 session-key 改为带 userId 前缀，便于批量失效
//                        SessionIdStrategyForceModifyUtils.changeSessionId(webSession, JSON.parseObject(userInfoStr, UserSessionDTO.class).getUserId());
//                        map.put(UserSessionDTO.HEADER_KEY, userInfoStr);
//                    }
//                });
//            });
//        });
//    }

    private Mono<Void> returnNoPermission(ServerWebExchange exchange, String msg) {
        // 返回鉴权失败的消息
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

//    private boolean isAllowPath(String requestUrl) {
//        for (String allowPath : filterProp.getAllowPaths())
//            if (requestUrl.startsWith(allowPath))
//                return true;
//        return false;
//    }
}