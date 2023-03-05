package com.qf.business.user.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qf.data.user.entity.Teacher;

/**
 * 教师表(Teacher)表服务接口
 *
 * @author makejava
 * @since 2023-03-02 17:53:49
 */
public interface TeacherService extends IService<Teacher> {

    Teacher queryTeacherByEmail(String email);
}

