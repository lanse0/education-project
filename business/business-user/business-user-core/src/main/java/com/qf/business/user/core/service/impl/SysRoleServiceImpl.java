package com.qf.business.user.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.business.user.core.dao.SysRoleDao;
import com.qf.business.user.core.service.SysRoleService;
import com.qf.data.user.entity.SysRole;
import org.springframework.stereotype.Service;

/**
 * 角色表(SysRole)表服务实现类
 *
 * @author makejava
 * @since 2022-10-07 14:46:22
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements SysRoleService {

}

