package com.qf.ability.auth.service.auth;

import com.qf.ability.auth.entity.AuthInfo;
import com.qf.ability.auth.service.IAuthService;
import com.qf.commons.data.base.BaseUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 教师端接口
 * author 14526
 * create_time 2023/2/28
 */
@Service("authService1")
@Slf4j
public class TeacherAuthServiceImpl implements IAuthService {

    @Override
    public BaseUser login(AuthInfo authInfo) {

        return null;
    }
}
