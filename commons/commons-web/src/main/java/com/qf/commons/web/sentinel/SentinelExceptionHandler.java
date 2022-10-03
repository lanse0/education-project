package com.qf.commons.web.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.fastjson.JSON;
import com.qf.commons.data.result.R;
import com.qf.commons.data.result.RCodes;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * author 14526
 * create_time 2022/10/4
 */
@Slf4j
public class SentinelExceptionHandler implements BlockExceptionHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws Exception {
        log.error("[sentinel exception] 发生了sentinel异常");
        R result = null;
        if (e instanceof FlowException){
            //流控异常信息
            result = R.createFail(RCodes.FLOW_ERROR);
        }else if (e instanceof DegradeException){
            //熔断降级异常信息
            result = R.createFail(RCodes.DEGRADE_ERROR);
        }else {
            //默认异常
            result = R.createFail(RCodes.FAIL);
        }

        //响应给前端
//        httpServletResponse.setStatus(500);//设置响应码
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().println(JSON.toJSONString(result));
        httpServletResponse.getWriter().close();
    }
}
