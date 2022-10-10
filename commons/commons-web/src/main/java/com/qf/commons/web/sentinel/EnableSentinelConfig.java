package com.qf.commons.web.sentinel;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * sentinel流控 需要的类上打注解 不采用自动装配方式
 * author 14526
 * create_time 2022/10/11
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({SentinelConfigInit.class, SentinelExceptionHandler.class})
public @interface EnableSentinelConfig {
}
