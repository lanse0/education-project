package com.qf.business.student.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qf.data.student.entity.User;

import java.util.List;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2022-09-23 15:12:47
 */
public interface UserService extends IService<User> {
    List<User> queryUserAndCourse();
}

