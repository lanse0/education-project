package com.qf.business.user.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.business.user.core.dao.SysRolePowerDao;
import com.qf.business.user.core.service.SysRolePowerService;
import com.qf.data.user.entity.SysRolePower;
import org.springframework.stereotype.Service;

/**
 * 角色和权限的中间表(SysRolePower)表服务实现类
 *
 * @author makejava
 * @since 2022-10-07 15:56:34
 */
@Service("sysRolePowerService")
public class SysRolePowerServiceImpl extends ServiceImpl<SysRolePowerDao, SysRolePower> implements SysRolePowerService {

}

