package com.qf.business.redenvlopes.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
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
     * 抢红包
     * @param redId
     * @return
     */
    int robRed(Integer redId);

    /**
     * 红包回退
     * @param redId
     * @return
     */
    int redBack(Integer redId);
}

