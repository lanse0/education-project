package com.qf.business.student.core.application;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * author 14526
 * create_time 2022/9/21
 */
@Component
public class MyListener implements ApplicationListener<MyEvent> {

    @Override
    public void onApplicationEvent(MyEvent event) {
        System.out.println("接收到监听事件："+event.i);
    }
}
