package com.qf.business.user.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.business.user.core.dao.WxScoreDetailsDao;
import com.qf.data.user.entity.WxScoreDetails;
import com.qf.business.user.core.service.WxScoreDetailsService;
import org.springframework.stereotype.Service;

/**
 * 积分流水表(WxScoreDetails)表服务实现类
 *
 * @author makejava
 * @since 2023-03-21 23:40:28
 */
@Service("wxScoreDetailsService")
public class WxScoreDetailsServiceImpl extends ServiceImpl<WxScoreDetailsDao, WxScoreDetails> implements WxScoreDetailsService {

}

