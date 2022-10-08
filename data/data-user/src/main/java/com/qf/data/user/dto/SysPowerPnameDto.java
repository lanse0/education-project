package com.qf.data.user.dto;

import com.qf.data.user.entity.SysPower;
import lombok.Data;

/**
 * 查询父级权限的名称的DTO
 * author 14526
 * create_time 2022/10/8
 */
@Data
public class SysPowerPnameDto extends SysPower {
    //父权限的名称
    private String parentPowerName;
}
