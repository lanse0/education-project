package com.qf.business.user.core.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.business.user.core.dao.SysUserDao;
import com.qf.business.user.core.service.SysUserRoleService;
import com.qf.business.user.core.service.SysUserService;
import com.qf.data.user.dto.SysUserDeptDto;
import com.qf.data.user.entity.SysUser;
import com.qf.data.user.entity.SysUserRole;
import com.qf.data.user.vo.input.SysSetUserRoleInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统用户表(SysUser)表服务实现类
 *
 * @author makejava
 * @since 2022-10-07 14:46:22
 */
@Service("sysUserService")
@Transactional
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {

    @Autowired
    private SysUserRoleService userRoleService;

    /**
     * 获取带部门名称的user列表
     *
     * @return
     */
    @Override
    public List<SysUserDeptDto> queryUserDeptList() {
        return getBaseMapper().queryUserDeptList();
    }

    /**
     * 设置用户的角色信息
     *
     * @param userRoleInput
     */
    @Override
    public void updateUserRoles(SysSetUserRoleInput userRoleInput) {
        //先根据用户删除所有关联角色
        userRoleService.deleteUserRoleByUid(userRoleInput.getUid());
        //若角色集合为空 直接返回
        if (CollectionUtils.isEmpty(userRoleInput.getRids())) return;
        //循环转换
        //userRoleInput.getRids().stream().map(new SysUserRole().setUid(userRoleInput.getUid())::setRid).collect(Collectors.toList());
        List<SysUserRole> userRoles = userRoleInput.getRids().stream()
                .map(rid -> new SysUserRole().setUid(userRoleInput.getUid()).setRid(rid))
                .collect(Collectors.toList());
        //调用批量保存
        userRoleService.saveBatch(userRoles);
    }
}

