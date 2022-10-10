package com.qf.ability.auth.controller;

import com.qf.ability.auth.entity.AuthInfo;
import com.qf.ability.auth.service.IAuthService;
import com.qf.commons.data.base.BaseUser;
import com.qf.commons.data.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 后台管理系统登录接口
 * author 14526
 * create_time 2022/10/10
 */
@RestController
@RequestMapping("/auth/sys")
@Slf4j
public class SysUserAuthController {

    @Autowired
    private IAuthService authService;
    /**
     * 后台管理系统的登录
     *
     * @return
     */
    @RequestMapping("/login")
    public R login(AuthInfo authInfo) {
        log.debug("[sys auth login] 后台系统登录请求 - {}-{}", authInfo.getUsername(), authInfo.getPassword());

        //登录逻辑
        BaseUser user = authService.login(authInfo);

        //根据用户登录凭证 生成令牌


        return R.create(user);
    }
}
