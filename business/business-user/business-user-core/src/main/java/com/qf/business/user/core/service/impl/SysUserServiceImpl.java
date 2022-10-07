package com.qf.business.user.core.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.business.user.core.dao.SysUserDao;
import com.qf.business.user.core.service.SysUserService;
import com.qf.data.user.entity.SysUser;
import org.springframework.stereotype.Service;

/**
 * 系统用户表(SysUser)表服务实现类
 *
 * @author makejava
 * @since 2022-10-07 14:46:22
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {

}

