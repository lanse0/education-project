package com.qf.business.user.core.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.qf.data.user.dto.SysRoleDeptDto;
import com.qf.data.user.entity.SysRole;
import com.qf.data.user.vo.input.SysSetRolePowerInput;
import com.qf.data.user.dto.SysRoleCheckDto;

import java.util.List;

/**
 * 角色表(SysRole)表服务接口
 *
 * @author makejava
 * @since 2022-10-07 14:46:21
 */
public interface SysRoleService extends IService<SysRole> {
    List<SysRoleDeptDto> queryRoleDeptList();

    int updateRolePowers(SysSetRolePowerInput rolePowerInput);

    List<SysRoleCheckDto> queryRolesByUid(Integer uid);
}

