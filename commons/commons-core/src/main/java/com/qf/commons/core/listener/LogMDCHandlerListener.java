package com.qf.commons.core.listener;

import org.slf4j.MDC;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.GenericApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.TreeMap;

/**
 * 监听SpringBoot环境准备事件，进行相应的处理 - 设置MDC
 * 这个类不能配置spring扫描 执行这个步骤时 spring ioc容器还未初始化 应在factories文件中配置应用监听
 * author 14526
 * create_time 2022/9/21
 */
public class LogMDCHandlerListener implements GenericApplicationListener {

    @Override
    public boolean supportsEventType(ResolvableType eventType) {
        return eventType.isAssignableFrom(ApplicationEnvironmentPreparedEvent.class);
    }

    /**
     *
     * @param sourceType  - 代表当前发生的事件类型
     * @return  如果返回true，表示当前事件类型是我这个类感兴趣的事件类型，就会触发onApplicationEven方法，
     *          如果返回false，就表示该事件类型不感兴趣，onApplicationEvent就不会调用
     */
    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        return true;
    }

    /**
     * 事件处理方法
     * @param event
     */
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("监听到了一些事情："+event);
        //强制转换 环境准备事件  强转到子类 才有下面的方法
        ApplicationEnvironmentPreparedEvent preparedEvent = (ApplicationEnvironmentPreparedEvent)event;
        //获得当前的环境
        ConfigurableEnvironment environment = preparedEvent.getEnvironment();
        //获得微服务的名称
        String serviceName = environment.getProperty("spring.application.name");
        //设置MDC 即可
        MDC.put("filename",serviceName);
    }

    @Override
    public int getOrder() {
        //获取日志xml配置信息的优先级是+20  值越小优先级越高 获取的日志文件名的步骤需要在20之前
        return Ordered.HIGHEST_PRECEDENCE + 19;
    }
}
