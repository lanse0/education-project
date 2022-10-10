package com.qf.data.user.dto;

import com.qf.data.user.entity.SysPower;
import com.qf.data.user.entity.SysUser;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 用户信息 以及 用户下的权限信息的dto
 * author 14526
 * create_time 2022/10/10
 */
@Data
@ToString(callSuper = true)
public class SysUserPowerDto extends SysUser {

    //权限列表
    private List<SysPower> powers;
}
