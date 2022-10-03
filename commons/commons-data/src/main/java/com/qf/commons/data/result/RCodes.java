package com.qf.commons.data.result;

import lombok.Getter;

/**
 * author 14526
 * create_time 2022/9/21
 */
@Getter
public enum RCodes {
    SUCC(200, "成功"),
    FAIL(500, "服务器异常"),
    DATA_CHECKED_ERROR(401, "参数校验异常"),
    FLOW_ERROR(402, "请求被限流"),
    DEGRADE_ERROR(403, "请求被熔断");

    private Integer code;
    private String msg;

    RCodes(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
