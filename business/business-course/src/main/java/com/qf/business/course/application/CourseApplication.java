package com.qf.business.course.application;

import org.apache.catalina.core.ApplicationContext;
import org.apache.catalina.core.StandardContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * author 14526
 * create_time 2022/9/20
 */
@SpringBootApplication
public class CourseApplication {
    public static void main(String[] args) {
        SpringApplication.run(CourseApplication.class,args);
    }
}

//自定义事件
class MyEvent extends ApplicationContext{

    public MyEvent(StandardContext context) {
        super(context);
    }

    private Integer i = 100;
}
