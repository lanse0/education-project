package com.qf.commons.redis.utils.annotation;

import java.lang.annotation.*;

/**
 * author 14526
 * create_time 2022/10/28
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface KLock {
    //锁的名字
    String value();
}
