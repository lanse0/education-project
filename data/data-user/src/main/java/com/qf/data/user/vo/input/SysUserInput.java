package com.qf.data.user.vo.input;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * author 14526
 * create_time 2022/10/9
 */
@Data
public class SysUserInput {
    //用户名
    @NotBlank(message = "用户名不能为空")
    private String username;
    //密码
    @NotBlank(message = "密码不能为空")
    private String password;
    //姓名
    @NotBlank(message = "姓名不能为空")
    private String name;
    //部门id
    private Integer depId;
    //性别 0-男 1-女
    private Integer sex;
}
