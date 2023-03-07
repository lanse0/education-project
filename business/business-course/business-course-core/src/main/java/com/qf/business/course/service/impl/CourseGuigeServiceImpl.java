package com.qf.business.course.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.business.course.dao.CourseGuigeDao;
import com.qf.business.course.service.CourseGuigeService;
import com.qf.data.course.entity.CourseGuige;
import org.springframework.stereotype.Service;

/**
 * 课程分类的规格表(CourseGuige)表服务实现类
 *
 * @author makejava
 * @since 2023-03-06 20:14:12
 */
@Service("courseGuigeService")
public class CourseGuigeServiceImpl extends ServiceImpl<CourseGuigeDao, CourseGuige> implements CourseGuigeService {

}

