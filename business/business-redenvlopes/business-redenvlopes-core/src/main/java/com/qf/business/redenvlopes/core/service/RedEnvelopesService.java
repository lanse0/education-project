package com.qf.business.redenvlopes.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.data.redenvlopes.dto.RedEnvelopesDto;
import com.data.redenvlopes.entity.RedEnvelopes;
import com.data.redenvlopes.vo.input.RedEnvlopesInput;

/**
 * 红包表(RedEnvelopes)表服务接口
 *
 * @author makejava
 * @since 2023-03-21 23:45:36
 */
public interface RedEnvelopesService extends IService<RedEnvelopes> {

    /**
     * 发红包
     * @param redEnvlopesInput
     * @return
     */
    int sendRed(RedEnvlopesInput redEnvlopesInput);

    /**
     * 抢红包 - 分布式锁方案
     * @param redId
     * @return 正数-抢到的积分 -1已抢完 -2红包过期 -3用户已抢过
     */
    int robRed(Integer redId);

    /**
     * 抢红包 - redis方案
     * @param redId
     * @param uid
     * @param score
     * @return
     */
    int robRed(Integer redId,Integer uid,Integer score);

    /**
     * 红包回退
     * @param redId
     * @return
     */
    int redBack(Integer redId);

    /**
     * 根据红包id  查询出包含剩余积分数和红包数量的dto类
     * @param redid 红包id
     * @return 红包dto类 提供给抢红包业务使用，包含剩余积分和剩余红包数属性
     */
    RedEnvelopesDto queryRedById(Integer redid);
}

