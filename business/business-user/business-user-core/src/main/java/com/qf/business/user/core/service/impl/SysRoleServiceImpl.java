package com.qf.business.user.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.business.user.core.dao.SysRoleDao;
import com.qf.business.user.core.service.SysRolePowerService;
import com.qf.business.user.core.service.SysRoleService;
import com.qf.data.user.dto.SysRoleDeptDto;
import com.qf.data.user.entity.SysRole;
import com.qf.data.user.entity.SysRolePower;
import com.qf.data.user.vo.input.SysSetRolePowerInput;
import com.qf.data.user.dto.SysRoleCheckDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色表(SysRole)表服务实现类
 *
 * @author makejava
 * @since 2022-10-07 14:46:22
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements SysRoleService {

    @Autowired
    private SysRolePowerService sysRolePowerService;

    @Override
    public List<SysRoleDeptDto> queryRoleDeptList() {
        return getBaseMapper().queryRoleDeptList();
    }

    /**
     * 设置指定角色的权限列表 先清空相关权限 后添加新权限
     * @param rolePowerInput
     * @return
     */
    @Override
    @Transactional
    public int updateRolePowers(SysSetRolePowerInput rolePowerInput) {

        //先删除原来的权限
        sysRolePowerService.deleteByRid(rolePowerInput.getRid());

        //若权限列表为空 清空权限后返回
        if (CollectionUtils.isEmpty(rolePowerInput.getPids())) return 0;

        //stream流转换对象
        //SysSetRolePowerInput(rid : 1, pids:[1,2,3,4] ) 转换为 =>
        //List<SysRolePower> -[{rid:1,powid:1},{rid:1,powid:2},{rid:1,powid:3}]
        List<SysRolePower> rolePowers = rolePowerInput.getPids().stream().map(pid -> {
            SysRolePower sysRolePower = new SysRolePower();
            sysRolePower.setRid(rolePowerInput.getRid());
            sysRolePower.setPowid(pid);
            return sysRolePower;
        }).collect(Collectors.toList());
        //批量保存
        sysRolePowerService.saveBatch(rolePowers);

        return 1;
    }

    /**
     * 根据用户id 查询用户列表以及当前用户是否拥有当前角色
     * @return
     */
    @Override
    public List<SysRoleCheckDto> queryRolesByUid(Integer uid) {
        return getBaseMapper().queryRolesByUid(uid);
    }
}

