package com.qf.business.user.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qf.data.user.entity.WxUser;
import com.qf.data.user.vo.input.WxScoreUpdateInput;
import com.qf.data.user.vo.input.WxUserInput;

/**
 * 小程序端的用户表(WxUser)表服务接口
 *
 * @author makejava
 * @since 2023-03-17 21:45:17
 */
public interface WxUserService extends IService<WxUser> {

    WxUser login(WxUserInput wxUserInput);

    /**
     * 保存用户修改的信息
     * @param wxUserInput
     * @return
     */
    boolean updateWxUser(WxUserInput wxUserInput);

    /**
     * 修改用户积分 增加/扣减
     * @param wxScoreUpdateInput
     * @return
     */
    int updateWxScore(WxScoreUpdateInput wxScoreUpdateInput);
}

