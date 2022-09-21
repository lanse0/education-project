package com.qf.commons.web.aspect;

import com.qf.commons.web.utils.HttpUtils;
import com.qf.commons.web.utils.RequestUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;

import javax.servlet.http.HttpServletRequest;

/**
 * author 14526
 * create_time 2022/9/21
 */
@Aspect
public class LogMDCAspect {

    @Around("@within(org.springframework.web.bind.annotation.RestController)")
    public Object logAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取客户端的IP地址
        HttpServletRequest request = RequestUtils.getRequest();
        String ip = request.getRemoteAddr();
        MDC.put("ip", HttpUtils.getIpAddr(request));

        //放行
        try {
            return joinPoint.proceed();
        } finally {
            //线程复用时避免重复读
            MDC.clear();
        }
    }
}
