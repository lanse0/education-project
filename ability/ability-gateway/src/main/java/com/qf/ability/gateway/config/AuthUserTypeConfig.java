package com.qf.ability.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * author 14526
 * create_time 2022/10/11
 */
@Data
@Component
@ConfigurationProperties(prefix = "request") //把配置文件中配置的客户端类型读取到本类的map中
public class AuthUserTypeConfig {

    private Map<String, String> fromType;
}
