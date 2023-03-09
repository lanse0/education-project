package com.qf.ability.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * 设置GateWay跨域过滤器的几种方式
 * author 14526
 * create_time 2023/3/9
 */
@Configuration
public class ResourcesConfig {

//    @Bean
//    public CorsWebFilter getCorsWebFilter() {
//        //进行跨域配置
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.addAllowedMethod("*");//允许所有方法的类型跨域 GET POST
//        corsConfiguration.addAllowedHeader("*");//允许任何请求头的请求跨域
//        corsConfiguration.addAllowedOrigin("*");//允许任何请i去的主机地址域
//        corsConfiguration.setAllowCredentials(true);//允许cookies跨域
//
//        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
//        corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
//        return new CorsWebFilter(corsConfigurationSource);
//    }

//    官方的
//    @Bean
//    public WebFilter corsFilter2() {
//        return (ServerWebExchange ctx, WebFilterChain chain) -> {
//            ServerHttpRequest request = ctx.getRequest();
//            if (CorsUtils.isCorsRequest(request)) {
//                HttpHeaders requestHeaders = request.getHeaders();
//                ServerHttpResponse response = ctx.getResponse();
//                HttpMethod requestMethod = requestHeaders.getAccessControlRequestMethod();
//                HttpHeaders headers = response.getHeaders();
//                headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, requestHeaders.getOrigin());
//                headers.addAll(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS,
//                        requestHeaders.getAccessControlRequestHeaders());
//                if (requestMethod != null) {
//                    headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, requestMethod.name());
//                }
//                headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
//                headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "*");
//                if (request.getMethod() == HttpMethod.OPTIONS) {
//                    response.setStatusCode(HttpStatus.OK);
//                    return Mono.empty();
//                }
//            }
//            return chain.filter(ctx);
//        };
//    }

//    @Bean
//    public WebFilter corsFilter() {
//        return (ServerWebExchange ctx, WebFilterChain chain) -> {
//            ServerHttpRequest request = ctx.getRequest();
//            if (CorsUtils.isCorsRequest(request)) {
//                ServerHttpResponse response = ctx.getResponse();
//                HttpHeaders headers = response.getHeaders();
//                headers.add("Access-Control-Allow-Origin", "*");
//                headers.add("Access-Control-Allow-Methods", "*");
//                headers.add("Access-Control-Max-Age", "18000L");
//                headers.add("Access-Control-Allow-Headers", "*");
//                headers.add("Access-Control-Expose-Headers", "*");
//                headers.add("Access-Control-Allow-Credentials", "true");
//                if (request.getMethod() == HttpMethod.OPTIONS||request.getMethod()==HttpMethod.GET) {
//                    response.setStatusCode(HttpStatus.OK);
//                    return Mono.empty();
//                }
//            }
//            return chain.filter(ctx);
//        };
//    }

}
