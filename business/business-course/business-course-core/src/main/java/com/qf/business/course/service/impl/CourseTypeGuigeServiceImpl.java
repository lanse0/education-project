package com.qf.business.course.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ken.mybatis.annotation.AutoMapping;
import com.qf.business.course.dao.CourseTypeGuigeDao;
import com.qf.business.course.service.CourseTypeGuigeService;
import com.qf.data.course.dto.CourseGuigeDto;
import com.qf.data.course.entity.CourseTypeGuige;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 课程分类和规格关联表(CourseTypeGuige)表服务实现类
 *
 * @author makejava
 * @since 2023-03-06 20:14:13
 */
@Service("courseTypeGuigeService")
public class CourseTypeGuigeServiceImpl extends ServiceImpl<CourseTypeGuigeDao, CourseTypeGuige> implements CourseTypeGuigeService {
    @Autowired
    private CourseTypeGuigeDao courseTypeGuigeDao;

    @Override
    public List<CourseGuigeDto> queryGuigesByTid(Integer tid) {
        return courseTypeGuigeDao.queryGuigesByTid(tid);
    }
}

