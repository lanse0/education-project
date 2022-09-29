package com.qf.business.student.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.business.student.core.dao.StudentCourseDao;
import com.qf.business.student.core.service.StudentCourseService;
import com.qf.data.student.entity.StudentCourse;
import org.springframework.stereotype.Service;

/**
 * 学生选课表(StudentCourse)表服务实现类
 *
 * @author makejava
 * @since 2022-09-23 15:15:12
 */
@Service("studentCourseService")
public class StudentCourseServiceImpl extends ServiceImpl<StudentCourseDao, StudentCourse> implements StudentCourseService {

}

