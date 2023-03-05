package com.qf.business.user.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.business.user.core.dao.TeacherDao;
import com.qf.business.user.core.service.TeacherService;
import com.qf.data.user.entity.Teacher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 教师表(Teacher)表服务实现类
 *
 * @author makejava
 * @since 2023-03-02 17:53:49
 */
@Service("teacherService")
@Slf4j
public class TeacherServiceImpl extends ServiceImpl<TeacherDao, Teacher> implements TeacherService {

    /**
     * 通过邮箱查询教师信息 兼容登录和注册
     *
     * @param email
     * @return
     */
    @Override
    public Teacher queryTeacherByEmail(String email) {

        //通过邮箱查询教师信息
        Teacher teacher = this.query().eq("email", email).one();

        if (teacher == null) {
            //当前邮箱未注册 创建当前邮箱的教师信息 进行注册
            teacher = new Teacher()
                    .setEmail(email)
                    .setSex(0)
                    .setName("游客" + (int) (Math.random() * 10000))
                    .setInfo("这个人很懒，什么都没有留下");
            this.save(teacher);
        }
        log.debug("[teacher login] 获取教师的信息 - {}", teacher);

        return teacher;
    }
}

