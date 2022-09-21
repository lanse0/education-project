package com.qf.commons.web.exception;

import com.qf.commons.core.exception.ServiceException;
import com.qf.commons.data.result.R;
import com.qf.commons.data.result.RCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 * author 14526
 * create_time 2022/9/21
 */
//RestController的增强
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public R serviceExceptionHandler(ServiceException e){
        return R.createFail(e.getCode(),e.getMsg());
    }

    @ExceptionHandler()
    public R globExceptionHandler(Throwable t) {
        //捕获异常 日志记录当前异常信息
//        System.out.println("发生了全局异常!" + t.getMessage());
        log.error("全局异常被捕获！",t);
        return R.createFail(RCodes.FAIL);
    }
}
