package com.qf.commons.redis.utils.config;

import com.qf.commons.redis.utils.lock.LockAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动装配切面类
 * author 14526
 * create_time 2022/10/28
 */
@Configuration
public class RedisConfiguration {

    @Bean
    public LockAspect getLockAspect(){
        return new LockAspect();
    }
}
