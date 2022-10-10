package com.qf.business.user.core.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.qf.data.user.entity.SysUserRole;

/**
 * 用户和角色的中间表(SysUserRole)表服务接口
 *
 * @author makejava
 * @since 2022-10-07 14:46:22
 */
public interface SysUserRoleService extends IService<SysUserRole> {
    int deleteUserRoleByUid(Integer uid);
}

