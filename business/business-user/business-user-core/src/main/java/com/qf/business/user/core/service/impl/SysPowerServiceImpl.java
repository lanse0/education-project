package com.qf.business.user.core.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.business.user.core.dao.SysPowerDao;
import com.qf.business.user.core.service.SysPowerService;
import com.qf.data.user.dto.SysPowerCheckDto;
import com.qf.data.user.dto.SysPowerPnameDto;
import com.qf.data.user.entity.SysPower;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 权限表(SysPower)表服务实现类
 *
 * @author makejava
 * @since 2022-10-07 14:46:21
 */
@Service("sysPowerService")
@Transactional
public class SysPowerServiceImpl extends ServiceImpl<SysPowerDao, SysPower> implements SysPowerService {

    /**
     * 查询权限列表 以及 每条权限的父权限名称
     * @return
     */
    @Override
    public List<SysPowerPnameDto> queryPnameList() {
        return getBaseMapper().queryPnameList();
    }

    /**
     * 根据角色id 查询权限列表 并且获取当前角色拥有的权限
     * @param rid
     * @return
     */
    @Override
    public List<SysPowerCheckDto> queryPowersCheckListByRid(Integer rid) {
        return getBaseMapper().queryPowersCheckListByRid(rid);
    }
}

