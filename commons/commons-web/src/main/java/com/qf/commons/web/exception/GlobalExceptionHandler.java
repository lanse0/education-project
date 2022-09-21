package com.qf.commons.web.exception;

import com.qf.commons.core.exception.ServiceException;
import com.qf.commons.data.result.R;
import com.qf.commons.data.result.RCodes;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 * author 14526
 * create_time 2022/9/21
 */
//RestController的增强
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public R serviceExceptionHandler(ServiceException e){
        System.out.println("发生了全局的业务异常"+e.getMessage());
        return R.createFail(e.getCode(),e.getMsg());
    }

    @ExceptionHandler()
    public R globExceptionHandler(Throwable t) {
        //捕获异常 日志记录当前异常信息
        System.out.println("发生了全局异常!" + t.getMessage());
        return R.createFail(RCodes.FAIL);
    }
}
