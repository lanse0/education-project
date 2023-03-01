package com.qf.ability.auth.controller;

import com.alibaba.fastjson.JSON;
import com.qf.ability.auth.entity.AuthInfo;
import com.qf.ability.auth.service.IAuthService;
import com.qf.commons.core.utils.JwtUtils;
import com.qf.commons.data.base.BaseUser;
import com.qf.commons.data.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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

//    @Autowired
//    private IAuthService authService;

    //直接通过容器获取客户端对应的authService实现类
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 后台管理系统的登录
     *
     * @return
     */
    @RequestMapping("/login")
    public R login(AuthInfo authInfo) {
        log.debug("[sys auth login] 接收到登录请求 - {}", authInfo);
//        //获取请求头 方法参数添加 HttpServletRequest 即可
//        String fromType = request.getHeader("fromType");
//        System.out.println(fromType);

        //通过容器获取客户端对应的实现类
        IAuthService authService = (IAuthService) applicationContext.getBean("authService"+authInfo.getFromType());

        //登录逻辑
        BaseUser user = authService.login(authInfo);

        //根据用户登录凭证 生成令牌 转为json字符串放进去 方便处理
        String loginToken = JwtUtils.createJwtToken("user", JSON.toJSONString(user));

        return R.create(loginToken);
    }
}
