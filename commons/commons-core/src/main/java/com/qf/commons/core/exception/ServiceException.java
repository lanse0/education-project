package com.qf.commons.core.exception;

import com.qf.commons.data.result.RCodes;
import lombok.Data;

/**
 * author 14526
 * create_time 2022/9/21
 */
@Data
public class ServiceException extends RuntimeException {
    private Integer code;
    private String msg;

    public ServiceException(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }
    public ServiceException(RCodes rCodes){
        this.code = rCodes.getCode();
        this.msg = rCodes.getMsg();
    }
}
