package com.qf.business.user.core.task;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

/**
 * author 14526
 * create_time 2022/11/12
 */
@Configuration      //主要用于标记配置类，兼备Component的效果。
@EnableScheduling   //springboot自带定时任务 开启定时任务
public class TimeTask {

    /**
     * fixedDelay = 2000 每隔2s执行一次
     * Cron表达式 实现大部分定时需求
     * cron = "0 * * * * ?" 每分钟的第0秒执行一次
     */
    @Scheduled(cron = "0 * 20 * * ?")
    public void task(){
        System.out.println("---------定时任务-------"+new Date());
    }
}
