package com.qf.business.student.core.application;

import org.springframework.context.ApplicationEvent;

/**
 * author 14526
 * create_time 2022/9/21
 */
public class MyEvent extends ApplicationEvent {

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public MyEvent(Object source) {
        super(source);
    }

    public Integer i = 100;
}
