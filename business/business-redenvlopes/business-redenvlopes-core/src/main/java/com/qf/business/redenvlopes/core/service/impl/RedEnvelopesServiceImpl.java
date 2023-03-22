package com.qf.business.redenvlopes.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.business.redenvlopes.core.dao.RedEnvelopesDao;
import com.data.redenvlopes.entity.RedEnvelopes;
import com.qf.business.redenvlopes.core.service.RedEnvelopesService;
import org.springframework.stereotype.Service;

/**
 * 红包表(RedEnvelopes)表服务实现类
 *
 * @author makejava
 * @since 2023-03-21 23:45:36
 */
@Service("redEnvelopesService")
public class RedEnvelopesServiceImpl extends ServiceImpl<RedEnvelopesDao, RedEnvelopes> implements RedEnvelopesService {

}

