package com.qf.data.user.vo.input;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 微信端入参对象
 * author 14526
 * create_time 2023/3/17
 */
@Data
@Accessors(chain = true)
public class WxUserInput implements Serializable {

    private String code;
    private String header;
    private String nickname;

}

