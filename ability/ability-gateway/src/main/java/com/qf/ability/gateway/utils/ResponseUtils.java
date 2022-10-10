package com.qf.ability.gateway.utils;

import com.alibaba.fastjson.JSON;
import com.qf.commons.data.result.R;
import com.qf.commons.data.result.RCodes;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;

/**
 * author 14526
 * create_time 2022/10/11
 */
public class ResponseUtils {

    /**
     * 自定义响应给客户端结果
     *
     * @return
     */
    public static Mono response(ServerWebExchange exchange, R result) {
        //当前未登录,响应错误信息给客户端
        ServerHttpResponse response = exchange.getResponse();
        //设置响应码 500
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        //设置响应头 - 告诉客户端，返回的是一个json字符串
        response.getHeaders().add("Content-Type","application/json;charset=utf-8");
        DataBuffer dataBuffer = null;
        try {
            dataBuffer = response.bufferFactory().wrap(JSON.toJSONString(result).getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return response.writeWith(Mono.just(dataBuffer));
    }
}
