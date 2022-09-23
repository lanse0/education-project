package com.qf.commons.mybatis.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * author 14526
 * create_time 2022/9/23
 */
@Configuration
@MapperScan("com.qf.**.dao")
public class MyBatisAutoConfiguration {
}
