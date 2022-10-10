package com.qf.business.user.core.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.business.user.core.dao.SysUserRoleDao;
import com.qf.business.user.core.service.SysUserRoleService;
import com.qf.data.user.entity.SysUserRole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户和角色的中间表(SysUserRole)表服务实现类
 *
 * @author makejava
 * @since 2022-10-07 14:46:22
 */
@Service("sysUserRoleService")
@Transactional
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRole> implements SysUserRoleService {
    /**
     * 删除用户角色
     *
     * @param uid
     * @return
     */
    @Override
    public int deleteUserRoleByUid(Integer uid) {
        Map<String, Object> map = new HashMap<>();
        map.put("uid", uid);
        this.removeByMap(map);
        return 1;
    }
}

