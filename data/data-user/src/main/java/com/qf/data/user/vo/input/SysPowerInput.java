package com.qf.data.user.vo.input;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 权限新增的VO对象
 * author 14526
 * create_time 2022/10/9
 */
@Data
public class SysPowerInput {
    //父权限(模块)的id
    private Integer pid;
    //权限名称
    @NotBlank(message = "权限名称不能为空")
    private String powerName;
    //权限的资源路径
    private String powerRes;
    //权限(模块)的类型(级别) 0-一级权限 1-二级权限 2-三级权限
    @Range(min = 0, max = 2, message = "权限类型不正确")
    @NotNull(message = "权限类型不能为null")
    private Integer powerType;
    //权限的额外信息
    private String powerInfo;
}
