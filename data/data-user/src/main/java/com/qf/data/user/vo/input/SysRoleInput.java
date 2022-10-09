package com.qf.data.user.vo.input;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * author 14526
 * create_time 2022/10/9
 */
@Data
public class SysRoleInput {
    //角色名称
    @NotBlank(message = "角色名称不能为空")
    private String roleName;
    //角色标识
    private String roleTag;
    //角色描述
    private String roleInfo;
    //部门id
    private Integer depId;
}
