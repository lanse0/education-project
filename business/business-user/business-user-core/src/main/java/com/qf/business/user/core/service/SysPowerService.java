package com.qf.business.user.core.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.qf.data.user.dto.SysPowerPnameDto;
import com.qf.data.user.entity.SysPower;

import java.util.List;

/**
 * 权限表(SysPower)表服务接口
 *
 * @author makejava
 * @since 2022-10-07 14:46:21
 */
public interface SysPowerService extends IService<SysPower> {
    List<SysPowerPnameDto> queryPnameList();
}

