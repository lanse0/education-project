package com.qf.business.student.application;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 * author 14526
 * create_time 2022/9/20
 */
@SpringBootApplication
public class StuApplication {
    public static void main(String[] args) {
        SpringApplication.run(StuApplication.class, args);
    }
}
