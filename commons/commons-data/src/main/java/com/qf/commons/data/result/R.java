package com.qf.commons.data.result;

import com.ken.mybatis.protocol.BaseResult;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一返回的R对象 - 接口层
 * 继承BaseResult获得page对象
 * author 14526
 * create_time 2022/9/21
 */
@Data
public class R<T> extends BaseResult implements Serializable {

    private Integer code;
    private String msg;
    private T data;

    /**
     * 返回成功的R对象
     * @param data
     * @param <S>
     * @return
     */
    public static <S> R<S> create(S data){
        R<S> r = new R<>();
        r.setCode(RCodes.SUCC.getCode());
        r.setMsg(RCodes.SUCC.getMsg());
        r.setData(data);
        return r;
    }

    /**
     * 返回失败的R对象
     * @param rCodes
     * @param <S>
     * @return
     */
    public static <S> R<S> createFail(RCodes rCodes){
        R<S> r = new R<>();
        r.setCode(rCodes.getCode());
        r.setMsg(rCodes.getMsg());
        return r;
    }
    public static <S> R<S> createFail(RCodes rCodes,S data){
        R<S> r = new R<>();
        r.setCode(rCodes.getCode());
        r.setMsg(rCodes.getMsg());
        r.setData(data);
        return r;
    }


    public static <S> R<S> createFail(Integer code,String msg){
        R<S> r = new R<>();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

}
