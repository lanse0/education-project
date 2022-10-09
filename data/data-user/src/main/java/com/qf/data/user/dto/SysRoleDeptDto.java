package com.qf.data.user.dto;

import com.qf.data.user.entity.SysRole;
import lombok.Data;

/**
 * author 14526
 * create_time 2022/10/9
 */
@Data
public class SysRoleDeptDto extends SysRole {
    //所属部门
    private String deptName;
}
