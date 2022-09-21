package com.qf.commons.web.config;

import com.qf.commons.web.aspect.LogMDCAspect;
import com.qf.commons.web.exception.GlobalExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * author 14526
 * create_time 2022/9/21
 */
@Configuration
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
     * Log日志的AOP处理
     * @return
     */
    @Bean
    public LogMDCAspect getLogMDCAspect(){
        return new LogMDCAspect();
    }
}
