package com.qf.data.user.dto;

import com.qf.data.user.entity.SysRole;
import lombok.Data;

/**
 * author 14526
 * create_time 2022/10/10
 */
@Data
public class SysRoleCheckDto extends SysRole {
    private boolean checked;
}
