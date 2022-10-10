package com.qf.commons.core.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * author 14526
 * create_time 2022/9/20
 */
@Configuration
//扫包 需搭配自动装配使用(META-INF/spring.factories)
//全局配置 需要扫包的模块再引入本模块即可
@ComponentScan({"com.qf.business", "com.qf.ability"})
public class CoreAutoConfiguration {

}
