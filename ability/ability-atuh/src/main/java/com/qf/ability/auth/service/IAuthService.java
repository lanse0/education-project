package com.qf.ability.auth.service;

import com.qf.ability.auth.entity.AuthInfo;
import com.qf.commons.data.base.BaseUser;

/**
 * author 14526
 * create_time 2022/10/10
 */
public interface IAuthService {

    BaseUser login(AuthInfo authInfo);
}
