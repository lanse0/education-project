package com.qf.ability.auth.service.auth;

import com.qf.ability.auth.entity.AuthInfo;
import com.qf.ability.auth.service.IAuthService;
import com.qf.business.user.feign.TeacherFeign;
import com.qf.commons.core.exception.ServiceException;
import com.qf.commons.data.base.BaseUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 教师端接口
 * author 14526
 * create_time 2023/2/28
 */
@Service("authService1")
@Slf4j
public class TeacherAuthServiceImpl implements IAuthService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private TeacherFeign teacherFeign;

    /**
     * 登录（注册接口）
     * @param authInfo
     * @return
     */
    @Override
    public BaseUser login(AuthInfo authInfo) {
        //校验验证码
        String code = authInfo.getCode();
        //获取保存在redis中的验证码
        String okCode = redisTemplate.opsForValue().get("code::" + authInfo.getEmail());
        if (code.equals(okCode)) {
            //通过邮箱去数据库查询信息 返回给前端
            log.debug("[login succ] 登录（注册）成功！");
            //登录成功 删除验证码
            redisTemplate.delete("code::" + authInfo.getEmail());

            return teacherFeign.queryTeacherByEmail(authInfo.getEmail()).getData();
        }
        //验证码不正确
        log.debug("[login error] 验证码错误！");
        throw new ServiceException(500, "验证码错误！");
    }
}
