package com.qf.ability.gateway.filter;

import com.ken.event.action.apply.producer.EventTemplate;
import com.qf.ability.gateway.utils.ResponseUtils;
import com.qf.commons.data.result.R;
import com.qf.commons.data.result.RCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * redis抢红包的拦截器
 * author 14526
 * create_time 2023/3/28
 */
@Component
@Slf4j
public class RedRobFilter extends AbstractGatewayFilterFactory implements GatewayFilter {

    @Override
    public GatewayFilter apply(Object config) {
        return this;
        //RedRobFilter类直接实现GatewayFilter接口 再实现filter方法 返回this 和这里的new内部类效果一致
//        return new GatewayFilter() {
//            @Override
//            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//                return null;
//            }
//        };

        //lambda表达式写法
//        return (exchange, chain) -> {
//          return null;
//        };
    }

    @Override
    public String name() {
        return "RedRobFilter";
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //抢红包业务已经执行完 返回结果
        return null;
    }
}
