package com.qf.business.user.core.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.business.user.core.dao.SysPowerDao;
import com.qf.business.user.core.service.SysPowerService;
import com.qf.data.user.entity.SysPower;
import org.springframework.stereotype.Service;

/**
 * 权限表(SysPower)表服务实现类
 *
 * @author makejava
 * @since 2022-10-07 14:46:21
 */
@Service("sysPowerService")
public class SysPowerServiceImpl extends ServiceImpl<SysPowerDao, SysPower> implements SysPowerService {

}

