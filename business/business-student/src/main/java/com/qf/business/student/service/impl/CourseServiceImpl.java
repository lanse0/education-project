package com.qf.business.student.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.business.student.dao.CourseDao;
import com.qf.business.student.service.CourseService;
import com.qf.data.student.entity.Course;
import org.springframework.stereotype.Service;

/**
 * 课程表(Course)表服务实现类
 *
 * @author makejava
 * @since 2022-09-23 15:14:00
 */
@Service("courseService")
public class CourseServiceImpl extends ServiceImpl<CourseDao, Course> implements CourseService {

}

