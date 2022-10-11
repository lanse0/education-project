package com.qf.commons.web.config;

import com.qf.commons.web.aspect.LogMDCAspect;
import com.qf.commons.web.aspect.LoginUserAspect;
import com.qf.commons.web.exception.GlobalExceptionHandler;
import com.qf.commons.web.exception.ResponseHandler;
import com.qf.commons.web.sentinel.SentinelConfigInit;
import com.qf.commons.web.sentinel.SentinelExceptionHandler;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * author 14526
 * create_time 2022/9/21
 */
@Configuration
@EnableFeignClients(basePackages = "com.qf.**.feign") //开启feign客户端 所有类都会依赖这个自动装配类 这里配置等于需要的地方都配置了
public class WebAutoConfiguration {
    /**
     * 注册全局异常处理器
     * @return
     */
    @Bean
    public GlobalExceptionHandler getGlobalExceptionHandler(){
        return new GlobalExceptionHandler();
    }

    /**
     * 响应体增强方法
     * @return
     */
    @Bean
    public ResponseHandler getResponseHandler(){
        return new ResponseHandler();
    }

    /**
     * Log日志的AOP处理
     * @return
     */
    @Bean
    public LogMDCAspect getLogMDCAspect(){
        return new LogMDCAspect();
    }

    /**
     * 封装用户信息的AOP
     * @return
     */
    @Bean
    public LoginUserAspect getLoginUserAspect(){
        return new LoginUserAspect();
    }
}
