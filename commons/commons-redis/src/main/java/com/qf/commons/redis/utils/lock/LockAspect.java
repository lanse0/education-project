package com.qf.commons.redis.utils.lock;

import com.qf.commons.redis.utils.annotation.KLock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * author 14526
 * create_time 2022/10/28
 */
@Aspect
@Order(0) //优先级 值越小优先级越高 保证比事务注解的优先级高 让事务执行完再解锁
public class LockAspect {

    //spel表达式解析器
    private SpelExpressionParser spelExpressionParser = new SpelExpressionParser();
    //局部变量表参数名称发现器
    private LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();

    @Around("@annotation(com.qf.commons.redis.utils.annotation.KLock)")
    public Object lockAop(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        try {
            //获取方法的注解
            MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
            Method method = signature.getMethod();
            KLock kLock = method.getAnnotation(KLock.class);

            //-----------------解析spel表达式--------------
            //根据method对象获得当前方法中 所有的形参名称
            String[] parameterNames = discoverer.getParameterNames(method);
            System.out.println("方法的形参名称：" + Arrays.toString(parameterNames));

            //获取形参的值
            Object[] args = proceedingJoinPoint.getArgs();

            //准备表达式解析的上下文对象
            StandardEvaluationContext context = new StandardEvaluationContext();
            for (int i = 0; i < parameterNames.length; i++) {
                context.setVariable(parameterNames[i], args[i]);
            }

            //获得spel表达式 "'goods-' + #{good.gid}"
            String spelStr = kLock.value();
            Expression expression = spelExpressionParser.parseExpression(spelStr);
            String lockKey = (String) expression.getValue(context);
            //-----------------解析spel表达式--------------

            //添加分布式锁
            LockUtils.lock(lockKey);
            //放行
            return proceedingJoinPoint.proceed();
        } finally {
            //释放分布式锁
            LockUtils.unlock();
        }
    }
}
