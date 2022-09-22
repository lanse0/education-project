package com.qf.business.student.controller;

import com.qf.business.student.application.MyEvent;
import com.qf.commons.core.exception.ServiceException;
import com.qf.commons.data.result.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.juli.logging.LogFactory;
import org.apache.tomcat.util.security.PrivilegedGetTccl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author 14526
 * create_time 2022/9/20
 */
@RestController
@RequestMapping("/stu")
@Slf4j
public class StuController {

    //使用lombox 的@Slf4j 简化日志 直接使用log.info 无需getLogger
    //private Logger logger = LoggerFactory.getLogger(StuController.class);

    @Autowired
    private ApplicationContext applicationContext;

    @Value("${spring.datasource.url}")
    private String url;

    @RequestMapping("/list")
    public R list(Integer a) {

//        MDC.put("key",String.valueOf(Math.random()*1000));

        log.debug("debug级别的日志");
        log.info("info级别的日志");
        log.warn("warn级别的日志");
        log.error("error级别的日志");

//        if (a < 0) {
//            throw new ServiceException(403, "参数范围不合法");
//        }
//        System.out.println(1/0);

        //发布一个自定义事件 事件发布机制
        applicationContext.publishEvent(new MyEvent(applicationContext));

        return R.create(url);
    }

    @RequestMapping("/reg")
    public R register(String username,String password){
        log.debug("用户注册 {} - {}",username,password);
        return R.create("注册成功");
    }
}
