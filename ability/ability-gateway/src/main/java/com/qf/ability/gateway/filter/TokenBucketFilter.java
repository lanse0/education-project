package com.qf.ability.gateway.filter;

import com.qf.ability.gateway.utils.ResponseUtils;
import com.qf.ability.gateway.utils.TokenBucket;
import com.qf.commons.data.result.R;
import com.qf.commons.data.result.RCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 令牌桶的限流过滤器
 * author 14526
 * create_time 2022/10/27
 */
@Component
@Slf4j
public class TokenBucketFilter extends AbstractGatewayFilterFactory {

    //url - 令牌桶Map集合 保证线程安全
    private static Map<String, TokenBucket> tokenBucketMap = new ConcurrentHashMap<>();

    @Override
    public GatewayFilter apply(Object config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                //局部过滤器的实现方法
                String path = exchange.getRequest().getURI().getPath();
                log.debug("[token bucket] 进入限流拦截器！{}", path);
                //从url中 获取对应的令牌桶对象，如果不存在 创建一个新的令牌桶
                TokenBucket tokenBuck = null;
                if (tokenBucketMap.containsKey(path)) {
                    tokenBuck = tokenBucketMap.get(path);
                } else {
                    tokenBuck = new TokenBucket(path, 5, 1);
                    tokenBucketMap.put(path, tokenBuck);
                }

                //通过令牌桶获取令牌
                if (tokenBuck.getTokens(1)) {
                    //成功获取令牌 直接放行
                    log.debug("[token bucket] 令牌桶限流通过");
                    return chain.filter(exchange);
                }

                //令牌不足 不能放行 返回失败信息
                log.debug("[token bucket] 令牌桶令牌不足，拒绝通过");
                return ResponseUtils.response(exchange, R.createFail(RCodes.TOKEN_GET_ERROR));
            }
        };
    }

    @Override
    public String name() {
        //令牌桶过滤器的名称
        return "TokenBucketFilter";
    }
}
