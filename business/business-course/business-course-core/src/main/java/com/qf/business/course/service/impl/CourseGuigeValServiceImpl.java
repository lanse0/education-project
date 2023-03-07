package com.qf.business.course.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.business.course.dao.CourseGuigeValDao;
import com.qf.business.course.service.CourseGuigeValService;
import com.qf.data.course.entity.CourseGuigeVal;
import org.springframework.stereotype.Service;

/**
 * 具体规格可选项表(CourseGuigeVal)表服务实现类
 *
 * @author makejava
 * @since 2023-03-06 20:14:13
 */
@Service("courseGuigeValService")
public class CourseGuigeValServiceImpl extends ServiceImpl<CourseGuigeValDao, CourseGuigeVal> implements CourseGuigeValService {

}

