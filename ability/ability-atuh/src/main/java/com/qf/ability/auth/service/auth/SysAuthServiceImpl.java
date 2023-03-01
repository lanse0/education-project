package com.qf.ability.auth.service.auth;

import com.qf.ability.auth.entity.AuthInfo;
import com.qf.ability.auth.service.IAuthService;
import com.qf.business.user.feign.SysUserFeign;
import com.qf.commons.core.exception.ServiceException;
import com.qf.commons.data.base.BaseUser;
import com.qf.commons.data.result.R;
import com.qf.data.user.dto.SysUserPowerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统管理用户接口
 * author 14526
 * create_time 2022/10/10
 */
@Service("authService0") //给Bean一个名字  以便不同客户端用户注入不同的IAuthService
@Slf4j
public class SysAuthServiceImpl implements IAuthService {

    @Autowired
    private SysUserFeign sysUserFeign;

    /**
     * 处理后台系统登录的逻辑
     *
     * @param authInfo
     */
    @Override
    public BaseUser login(AuthInfo authInfo) {
        //通过用户名查询用户信息
        R<SysUserPowerDto> result = sysUserFeign.queryUserByUn(authInfo.getUsername());
        //判断user服务是否查询到了用户的信息和权限
//        if (result.getCode() != RCodes.SUCC.getCode()) {
//            throw new ServiceException(500, "用户不存在!");
//        }

        SysUserPowerDto userPowerDto = result.getData();
        log.debug("[auth user] 登录认证的结果 - {}", userPowerDto);

        //用户查询成功 校验密码
        if (userPowerDto == null || !authInfo.getPassword().equals(userPowerDto.getPassword())) {
            //密码不正确 或用户名不正确
            throw new ServiceException(500, "用户名不正确或密码不正确");
        }
        //密码清空不返回前端
        userPowerDto.setPassword("");
        return userPowerDto;
    }
}
