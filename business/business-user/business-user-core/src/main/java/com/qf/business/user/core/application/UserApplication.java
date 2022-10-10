package com.qf.business.user.core.application;

import com.qf.commons.web.sentinel.EnableSentinelConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * author 14526
 * create_time 2022/10/7
 */
@SpringBootApplication
//@EnableSentinelConfig //流控开关
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
