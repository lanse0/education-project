package com.qf.business.course.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.qf.data.course.dto.CourseGuigeDto;
import com.qf.data.course.entity.CourseTypeGuige;

import java.util.List;

/**
 * 课程分类和规格关联表(CourseTypeGuige)表服务接口
 *
 * @author makejava
 * @since 2023-03-06 20:14:13
 */
public interface CourseTypeGuigeService extends IService<CourseTypeGuige> {

    List<CourseGuigeDto> queryGuigesByTid(Integer tid);
}

