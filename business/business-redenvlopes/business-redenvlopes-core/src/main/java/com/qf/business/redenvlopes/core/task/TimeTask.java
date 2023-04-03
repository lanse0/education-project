package com.qf.business.redenvlopes.core.task;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * author 14526
 * create_time 2023/3/30
 */
@Component
@EnableScheduling //开启springboot自带的定时器
public class TimeTask {

    /**
     * fixedDelay = 2000 每隔2s执行一次
     * Cron表达式 实现大部分定时需求
     * cron = "秒 分 时 日 月 星期 年"
     * cron = "0 * * * * ?" 每分钟的第0秒执行一次
     */
    @Scheduled(cron = "0 * 20 * * ?") //每晚八点的每一分种执行一次
    public void task(){
        System.out.println("午时已到");
    }
}
