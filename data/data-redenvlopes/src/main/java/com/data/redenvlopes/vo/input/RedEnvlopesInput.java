package com.data.redenvlopes.vo.input;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * author 14526
 * create_time 2023/3/23
 */
@Data
@Accessors(chain = true)
public class RedEnvlopesInput implements Serializable {
    @NotNull(message = "请选择红包类型")
    private Integer type; //红包类型
    @NotNull(message = "请输入红包总额")
    @Min(value = 0,message = "金额不能小于0")
    private Integer scount; //积分总额
    @NotNull(message = "请输入红包份数")
    @Min(value = 1,message = "红包份数不正确")
    private Integer number; //红包分数
    private String info; //红包描述
}
