package com.qf.ability.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.qf.ability.gateway.config.AuthIgnoreConfig;
import com.qf.ability.gateway.config.AuthUserTypeConfig;
import com.qf.ability.gateway.utils.ResponseUtils;
import com.qf.commons.core.utils.JwtUtils;
import com.qf.commons.data.base.BaseUser;
import com.qf.commons.data.result.R;
import com.qf.commons.data.result.RCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;
import java.util.Map;

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
     * 读取配置的用户类型信息
     */
    @Autowired
    private AuthUserTypeConfig authUserTypeConfig;

    /**
     * 匹配url的工具对象，可以进行通配符的匹配
     */
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //===========获取登录用户===========
        log.debug("[gateway auth filter] 网关全局认证过滤器！获取token和登录用户");
        //获得请求来源
        HttpHeaders headers = exchange.getRequest().getHeaders();
        //获取请求数据
        String fromType = headers.getFirst("fromType");
        String jwtToken = headers.getFirst("loginToken");
        //校验JWT令牌
        String userJson = JwtUtils.parseJwtToken(jwtToken, "user");
        log.debug("[gateway auth filter] 获得用户令牌解析的信息 - {}", userJson);
        //登录的用户
        BaseUser baseUser = null;
        //若用户已登录 解析用户
        if (userJson != null && !"".equals(userJson)) {
            //判断来源类型(客户端) 将userJson 转换为 相应的dto对象
            Map<String, String> userTypeMap = authUserTypeConfig.getFromType();
            log.debug("[gateway auth filter] 获取请求来源对应的用户类型 - {}", userTypeMap);

            //根据请求来源 获取对应的实体类的全路径限定名
            //获取该类对应的class对象
            Class uCls = null;
            try {
                uCls = Class.forName(userTypeMap.get(fromType));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            //将userJson字符串转换为dto对象的父类BaseUser 统一处理
            //BaseUser baseUser = (BaseUser) JSON.parseObject(userJson, uCls);
            baseUser = (BaseUser) JSON.parseObject(userJson, uCls);
            log.debug("[gateway auth filter] 解析token获得用户对象 - {}", baseUser);
        }
        //===========获取登录用户结束===========


        //获取请求的url
        String url = exchange.getRequest().getURI().getPath();
        log.debug("[gateway auth filter] 网关全局认证过滤器! 请求的url - {}", url);

        //===========权限拦截：无需登录 即可访问的===========
        if (authIgnoreConfig != null) {
            List<String> ignorePaths = authIgnoreConfig.getIgnore();
            if (!CollectionUtils.isEmpty(ignorePaths)) {
                //循环遍历所有需要忽略的url
                for (String ignorePath : ignorePaths) {
                    log.debug("[gateway auth filter] 需要放行的url - {} 请求的url - {}", ignorePath, url);
                    //这里不能用equas  需要工具类来匹配路径是否包含 如：/a/** --> /a/b
                    if (antPathMatcher.match(ignorePath, url)) {
                        //用户未登录 且该资源不需要登录直接放行
                        if (baseUser==null){
                            log.debug("[gateway auth filter] 请求无需登录即可访问 - {}", url);
                            return chain.filter(exchange);
                        }
                        //若用户已登录 需要将登录信息继续往后传递 封装新的请求头
                        //封装一个新的请求头 不能用旧的
                        HttpHeaders newHttpHeader = new HttpHeaders();
                        newHttpHeader.putAll(headers);//将旧的请求头信息设置给新的请求头对象
                        newHttpHeader.add("uid", baseUser.getId() + "");//将登录用户的id放入请求头
                        newHttpHeader.remove("loginToken");//删除令牌 无需往后传

                        //准备一个新的请求
                        //原本的请求uri对象
                        URI oldUri = exchange.getRequest().getURI();
                        ServerHttpRequest newRequest = exchange.getRequest().mutate().uri(oldUri).build();
                        //将新的请求头封装到新的请求对象中
                        newRequest = new ServerHttpRequestDecorator(newRequest) {
                            @Override
                            public HttpHeaders getHeaders() {
                                return newHttpHeader;
                            }
                        };

                        //把新的请求放行 删除了原来请求里面多余的数据
                        return chain.filter(exchange.mutate().request(newRequest).build());
                    }
                }
            }
        }

        //===========需要登录 才能访问===========
//        //获得请求来源
//        HttpHeaders headers = exchange.getRequest().getHeaders();
//        //获取请求数据
//        String fromType = headers.getFirst("fromType");
//        String jwtToken = headers.getFirst("loginToken");
//        //校验JWT令牌
//        String userJson = JwtUtils.parseJwtToken(jwtToken, "user");
//        log.debug("[gateway auth filter] 获得用户令牌解析的信息 - {}", userJson);
        if (userJson == null) {
            //无令牌信息，用户未登录，返回认证异常信息
            R r = R.createFail(RCodes.AUTH_FAIL);
            return ResponseUtils.response(exchange, r);
        }

        //===========用户已登录===========
//        //判断来源类型(客户端) 将userJson 转换为 相应的dto对象
//        Map<String, String> userTypeMap = authUserTypeConfig.getFromType();
//        log.debug("[gateway auth filter] 获取请求来源对应的用户类型 - {}", userTypeMap);
//
//        //根据请求来源 获取对应的实体类的全路径限定名
//        //获取该类对应的class对象
//        Class uCls = null;
//        try {
//            uCls = Class.forName(userTypeMap.get(fromType));
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//        //将userJson字符串转换为dto对象的父类BaseUser 统一处理
//        BaseUser baseUser = (BaseUser) JSON.parseObject(userJson, uCls);
//        log.debug("[gateway auth filter] 解析token获得用户对象 - {}", baseUser);

        //===========判断当前请求需要校验用户权限===========
        boolean isPowers = true; //是否需要权限校验
        //获取需要登录即可访问的资源 若url包含在内 直接放行
        List<String> loginPaths = authIgnoreConfig.getLogin();
        if (!CollectionUtils.isEmpty(loginPaths)) {
            for (String loginPath : loginPaths) {
                if (antPathMatcher.match(loginPath, url)) {
                    //只需要登录即可访问的url 放行
                    log.debug("[gateway auth filter] 请求登录即可访问 - {}", url);
                    isPowers = false;
                    break;
                }
            }
        }
        //===========需要权限校验的===========
        if (isPowers && !baseUser.hasPowers(url)) {
            //需要进行权限校验，且用户不具有该url的权限
            R r = R.createFail(RCodes.NO_POWER_ERROR);
            return ResponseUtils.response(exchange, r);
        }


        //将用户信息 继续 往后传递
        //封装一个新的请求头 不能用旧的
        HttpHeaders newHttpHeader = new HttpHeaders();
        newHttpHeader.putAll(headers);//将旧的请求头信息设置给新的请求头对象
        newHttpHeader.add("uid", baseUser.getId() + "");//将登录用户的id放入请求头
        newHttpHeader.remove("loginToken");//删除令牌 无需往后传

        //准备一个新的请求
        //原本的请求uri对象
        URI oldUri = exchange.getRequest().getURI();
        ServerHttpRequest newRequest = exchange.getRequest().mutate().uri(oldUri).build();
        //将新的请求头封装到新的请求对象中
        newRequest = new ServerHttpRequestDecorator(newRequest) {
            @Override
            public HttpHeaders getHeaders() {
                return newHttpHeader;
            }
        };

        //把新的请求放行 删除了原来请求里面多余的数据
        return chain.filter(exchange.mutate().request(newRequest).build());
    }
}
