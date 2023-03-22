package com.qf.business.redenvlopes.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.business.redenvlopes.core.dao.RedEnvelopesDetailsDao;
import com.data.redenvlopes.entity.RedEnvelopesDetails;
import com.qf.business.redenvlopes.core.service.RedEnvelopesDetailsService;
import org.springframework.stereotype.Service;

/**
 * 红包明细表(RedEnvelopesDetails)表服务实现类
 *
 * @author makejava
 * @since 2023-03-21 23:45:36
 */
@Service("redEnvelopesDetailsService")
public class RedEnvelopesDetailsServiceImpl extends ServiceImpl<RedEnvelopesDetailsDao, RedEnvelopesDetails> implements RedEnvelopesDetailsService {

}

