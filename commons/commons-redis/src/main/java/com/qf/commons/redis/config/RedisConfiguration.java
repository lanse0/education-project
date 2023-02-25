package com.qf.commons.redis.config;

import com.qf.commons.redis.lock.LockAspect;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动装配切面类
 * author 14526
 * create_time 2022/10/28
 */
@Configuration
@EnableCaching //开启redis缓存
public class RedisConfiguration {

    @Bean
    public LockAspect getLockAspect(){
        System.out.println("redis分布式锁自动装配生效");
        return new LockAspect();
    }
}
