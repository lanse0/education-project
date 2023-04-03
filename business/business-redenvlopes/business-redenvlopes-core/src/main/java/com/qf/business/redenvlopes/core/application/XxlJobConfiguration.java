package com.qf.business.redenvlopes.core.application;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * author 14526
 * create_time 2022/11/12
 */
@Configuration
public class XxlJobConfiguration {

    /**
     * 配置实体
     */
    @Data
    @ConfigurationProperties(prefix = "xxl.job.executor") //把xxl配置文件中的配置读取到类的属性里面
    @Component
    public static class XxlJobConfig{
        @Value("${xxl.job.admin.address}")
        private String adminAddresses;
        private String accessToken;
        private String appname;
        private String address;
        private String ip;
        private int port;
        private String logPath;
        private int logRetentionDays;
    }

    @Autowired
    private XxlJobConfig xxlJobConfig;

    /**
     * xxl-job和spring整合的配置类 把上面的配置实体类读取的信息放入执行器
     * @return
     */
    @Bean
    public XxlJobSpringExecutor getXxlJobExecutor(){
        System.out.println("获取配置信息：" + xxlJobConfig);
        XxlJobSpringExecutor executor = new XxlJobSpringExecutor();
        executor.setAdminAddresses(xxlJobConfig.getAdminAddresses());
        executor.setAppname(xxlJobConfig.getAppname());
        executor.setIp(xxlJobConfig.getIp());
        executor.setPort(xxlJobConfig.getPort());
        executor.setAccessToken(xxlJobConfig.getAccessToken());
        executor.setLogPath(xxlJobConfig.getLogPath());
        executor.setLogRetentionDays(xxlJobConfig.getLogRetentionDays());
        return  executor;
    }
}
