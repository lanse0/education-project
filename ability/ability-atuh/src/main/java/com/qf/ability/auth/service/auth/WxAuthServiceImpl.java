package com.qf.ability.auth.service.auth;

import com.qf.ability.auth.entity.AuthInfo;
import com.qf.ability.auth.service.IAuthService;
import com.qf.business.user.feign.WxUserFeign;
import com.qf.commons.core.exception.ServiceException;
import com.qf.commons.data.base.BaseUser;
import com.qf.data.user.entity.WxUser;
import com.qf.data.user.vo.input.WxUserInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 微信小程序端接口
 * author 14526
 * create_time 2023/3/17
 */
@Service("authService2") //bean的名称后缀数字 代表哪个端 0 系统端 1 教师端 2 小程序端
public class WxAuthServiceImpl implements IAuthService {

    @Autowired
    private WxUserFeign wxUserFeign;

    @Override
    public BaseUser login(AuthInfo authInfo) {
        //调用用户服务 获取小程序的用户信息
        WxUser wxUser = wxUserFeign.queryWxUser(
                new WxUserInput()
                        .setCode(authInfo.getWxCode())
                        .setHeader(authInfo.getHeader())
                        .setNickname(authInfo.getNickname())
        ).getData();
        //可优化 登录异常用户服务会抛异常 到这里基本上登录成功
        if (wxUser == null) throw new ServiceException(500, "登录异常");
        return wxUser;
    }
}
