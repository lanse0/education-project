package com.qf.ability.gateway.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * author 14526
 * create_time 2022/9/30
 */
@SpringBootApplication
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    /**
     * 设置GateWay的跨域过滤器
     *
     * @return
     */
    @Bean
    public CorsWebFilter getCorsWebFilter() {
        //进行跨域配置
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedMethod("*");//允许所有方法的类型跨域 GET POST
        corsConfiguration.addAllowedHeader("*");//允许任何请求头的请求跨域
        corsConfiguration.addAllowedOrigin("*");//允许任何请i去的主机地址域
        corsConfiguration.setAllowCredentials(true);//允许cookies跨域

        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsWebFilter(corsConfigurationSource);
    }
}
