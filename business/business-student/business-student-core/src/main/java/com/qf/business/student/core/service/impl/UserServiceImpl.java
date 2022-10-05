package com.qf.business.student.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.business.student.core.dao.UserDao;
import com.qf.business.student.core.service.UserService;
import com.qf.data.student.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2022-09-23 15:12:47
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Override
    public List<User> queryUserAndCourse() {
        //直接getBaseMapper 可以不用写UserDao属性
        return getBaseMapper().queryUserAndCourse();
    }
}
