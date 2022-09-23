package com.qf.commons.web.exception;

import com.qf.commons.data.result.R;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 响应体增强
 * author 14526
 * create_time 2022/9/24
 */
@RestControllerAdvice
public class ResponseHandler implements ResponseBodyAdvice {

    /**
     * supports 方法如果返回true beforeBodyWrite方法才会执行
     * @param methodParameter
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return methodParameter.getMethod().getReturnType() == R.class;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        R result = (R)o;
        if (result.getCode()!=200){
            //如果相应结果为未成功 修改响应的响应码(原来的情况下程序未执行成功，但是http响应成功的话会直接返回200)
            serverHttpResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }
}
