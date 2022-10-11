package com.qf.commons.web.aspect.annotation;

import java.lang.annotation.*;

/**
 * author 14526
 * create_time 2022/10/11
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface GetUser {
}
