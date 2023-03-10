package com.qf.business.course.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.business.course.dao.CourseInfoGuigeDao;
import com.qf.data.course.entity.CourseInfoGuige;
import com.qf.business.course.service.CourseInfoGuigeService;
import org.springframework.stereotype.Service;

/**
 * 课程信息和规格关联表(CourseInfoGuige)表服务实现类
 *
 * @author makejava
 * @since 2023-03-10 13:52:41
 */
@Service("courseInfoGuigeService")
public class CourseInfoGuigeServiceImpl extends ServiceImpl<CourseInfoGuigeDao, CourseInfoGuige> implements CourseInfoGuigeService {

}

