package com.qf.business.user.core.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.qf.data.user.dto.SysUserDeptDto;
import com.qf.data.user.dto.SysUserPowerDto;
import com.qf.data.user.entity.SysUser;
import com.qf.data.user.vo.input.SysSetUserRoleInput;
import com.qf.data.user.vo.input.SysUserSearchInput;

import java.util.List;

/**
 * 系统用户表(SysUser)表服务接口
 *
 * @author makejava
 * @since 2022-10-07 14:46:22
 */
public interface SysUserService extends IService<SysUser> {
    List<SysUserDeptDto> queryUserDeptList();

    void updateUserRoles(SysSetUserRoleInput userRoleInput);

    SysUserPowerDto queryUserByUn(String username);

    List<SysUser> search(SysUserSearchInput searchInput);
}

