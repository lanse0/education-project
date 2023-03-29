package com.qf.ability.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.charset.StandardCharsets;

/**
 * 处理请求体的拦截器 帮助拿到post请求的参数
 * author 14526
 * create_time 2023/3/29
 */
@Component
@Slf4j
public class RequestGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        URI URIPath = request.getURI();
        String path = request.getPath().value();
        String method = request.getMethodValue();
        HttpHeaders header = request.getHeaders();
        String requestParams = String.valueOf(request.getQueryParams());
        log.info("***********************************请求信息**********************************");
        log.info("URI = {}", URIPath);
        log.info("path = {}", path);
        log.info("header = {}", header);
        log.info("params = {}", requestParams);

        //文件上传不做处理
        String contentType = header.getFirst("content-type");
        if (StringUtils.hasLength(contentType) && contentType.contains("multipart/form-data")) {
            return chain.filter(exchange);
        }

        //get请求不做处理
        if ("GET".equals(method)){
            return chain.filter(exchange);
        }

        //获取requestBody
        if (header.getContentLength() > 0) {
            return DataBufferUtils.join(exchange.getRequest().getBody()).flatMap(dataBuffer -> {
                byte[] bytes = new byte[dataBuffer.readableByteCount()];
                dataBuffer.read(bytes);
                String bodyString = new String(bytes, StandardCharsets.UTF_8);
                log.info("requestBody = {}", bodyString);
                exchange.getAttributes().put("POST_BODY", bodyString);
                DataBufferUtils.release(dataBuffer);
                Flux<DataBuffer> cachedFlux = Flux.defer(() -> Mono.just(exchange.getResponse().bufferFactory().wrap(bytes)));

                ServerHttpRequest mutatedRequest = new ServerHttpRequestDecorator(exchange.getRequest()) {
                    @Override
                    public Flux<DataBuffer> getBody() {
                        return cachedFlux;
                    }
                };
                log.info("****************************************************************************\n");
                return chain.filter(exchange.mutate().request(mutatedRequest).build());
            });
        }
        log.info("****************************************************************************\n");
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
