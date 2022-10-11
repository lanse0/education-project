package com.qf.commons.web.aspect;

import com.qf.commons.data.base.BaseUser;
import com.qf.commons.web.utils.RequestUtils;
import com.qf.commons.web.utils.UserUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 处理登录的用户信息的切面
 * author 14526
 * create_time 2022/10/11
 */
@Aspect
public class LoginUserAspect {

    //表示在类和方法上都可以使用注解
    @Around("@annotation(com.qf.commons.web.aspect.annotation.GetUser)||@within(com.qf.commons.web.aspect.annotation.GetUser)")
    public Object loginUserHandler(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取请求头
        HttpServletRequest request = RequestUtils.getRequest();
        String uid = request.getHeader("uid");//获取用户id
        String fromType = request.getHeader("fromType");//获取请求来源

        if (StringUtils.isEmpty(uid)||StringUtils.isEmpty(fromType)){
            //若参数为空 则直接放行
            return joinPoint.proceed();
        }

        BaseUser baseUser = new BaseUser();
        baseUser.setId(Integer.parseInt(uid));
        baseUser.setFromType(Integer.parseInt(fromType));

        //放入TreadLocal
        UserUtils.setUserThreadLocal(baseUser);

        try {
            return joinPoint.proceed();
        } finally {
            //清空用户信息
            UserUtils.clear();
        }
    }
}
