package com.qf.data.user.dto;

import com.qf.data.user.entity.SysUser;
import lombok.Data;

/**
 * author 14526
 * create_time 2022/10/9
 */
@Data
public class SysUserDeptDto extends SysUser {
    //部门名称
    private String deptName;
}
