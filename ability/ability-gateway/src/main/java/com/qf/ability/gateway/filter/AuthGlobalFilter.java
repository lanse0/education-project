package com.qf.ability.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.qf.ability.gateway.config.AuthIgnoreConfig;
import com.qf.ability.gateway.utils.ResponseUtils;
import com.qf.commons.core.exception.ServiceException;
import com.qf.commons.core.utils.JwtUtils;
import com.qf.commons.data.base.BaseUser;
import com.qf.commons.data.result.R;
import com.qf.commons.data.result.RCodes;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpHead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.List;

/**
 * 全局认证的过滤器
 * 1)全局过滤器
 * 2)局部过滤器
 * author 14526
 * create_time 2022/10/10
 */
@Component
@Slf4j
@RefreshScope   //远程配置中心的刷新注解
public class AuthGlobalFilter implements GlobalFilter {

    /**
     * 注入需要忽略认证的请求的url集合
     */
    @Autowired(required = false) //可能为空 required
    private AuthIgnoreConfig authIgnoreConfig;

    /**
     * 匹配url的工具对象，可以进行通配符的匹配
     */
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        log.debug("[gateway auth filter] 网关全局认证过滤器！");
        //获取当前请求的url
        String url = exchange.getRequest().getURI().getPath();
        log.debug("[gateway auth filter] 网关全局认证过滤器! - {}", url);

        if (authIgnoreConfig != null) {
            //无需登录 即可访问的
            List<String> ignorePaths = authIgnoreConfig.getIgnore();
            if (!CollectionUtils.isEmpty(ignorePaths)) {
                //循环遍历所有需要忽略的url
                for (String ignorePath : ignorePaths) {
                    //log.debug("[gateway auth filter] 需要放行的url - {} 请求的url - {}", ignorePath, url);
                    // 这里不能用equas  需要工具类来匹配路径是否包含 如：/a/** --> /a/b
                    if (antPathMatcher.match(ignorePath, url)) {
                        //直接放行
                        log.debug("[gateway auth filter] 请求无需登录即可访问 - {}", url);
                        return chain.filter(exchange);
                    }
                }
            }
        }

        //需要登录 才能访问 获得令牌
        //获得请求来源
        HttpHeaders headers = exchange.getRequest().getHeaders();
        String fromType = headers.getFirst("fromType");
        String jwtToken = headers.getFirst("loginToken");
        //校验JWT令牌
        String userJson = JwtUtils.parseJwtToken(jwtToken, "user");
        log.debug("[gateway auth filter] 获得用户令牌解析的信息 - {}", userJson);
        if (userJson == null) {
            //无令牌信息，用户未登录，返回认证异常信息
            R r = R.createFail(RCodes.AUTH_FAIL);
            return ResponseUtils.response(exchange, r);
        }


        //需要登录 且需要权限校验通过的

        //从请求中获取登录令牌

        //放行
        return chain.filter(exchange);
    }
}
