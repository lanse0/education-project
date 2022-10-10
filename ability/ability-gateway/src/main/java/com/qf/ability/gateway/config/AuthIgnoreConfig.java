package com.qf.ability.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * author 14526
 * create_time 2022/10/10
 */
@Data
@Component
@ConfigurationProperties(prefix = "auth.paths") //表示配置文件里的值自动读取到这个类里面
public class AuthIgnoreConfig {

    //无需登录即可访问的url
    private List<String> ignore;
    //需要登录即可访问的url
    private List<String> login;
}
