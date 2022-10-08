package com.qf.commons.web.exception;

import com.qf.commons.core.exception.ServiceException;
import com.qf.commons.data.result.R;
import com.qf.commons.data.result.RCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

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
    public R serviceExceptionHandler(ServiceException e) {
        return R.createFail(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(BindException.class)
    public R bindExceptionHandler(BindException e) {
        BindingResult result = e.getBindingResult();
        List<ObjectError> errors = result.getAllErrors();
        List<String> errorInfo = new ArrayList<>();
        for (ObjectError error : errors) {
            errorInfo.add(error.getDefaultMessage());
        }
        return R.createFail(RCodes.DATA_CHECKED_ERROR, errorInfo);
    }


    @ExceptionHandler()
    public R globExceptionHandler(Throwable t) {
        //捕获异常 日志记录当前异常信息
//        System.out.println("发生了全局异常!" + t.getMessage());
        log.error("全局异常被捕获！", t);
        return R.createFail(RCodes.FAIL);
    }

    //捕获数据插入的 SQL重复键异常 返回给前端 一般在service层先判断要插入的值是否存在 再手动抛异常
//    @ExceptionHandler(DuplicateKeyException.class)
//    public R sqlViolationHandler(DuplicateKeyException e) {
//        log.error("记录已存在" + e);
//        return R.createFail(RCodes.DATA_UNIQUE_ERROR);
//    }
}
