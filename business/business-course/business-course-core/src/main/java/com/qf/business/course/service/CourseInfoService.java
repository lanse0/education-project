package com.qf.business.course.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qf.data.course.entity.CourseInfo;
import com.qf.data.course.vo.input.CourseInfoInput;

import java.util.List;

/**
 * 课程信息表(CourseInfo)表服务接口
 *
 * @author makejava
 * @since 2023-03-10 13:52:41
 */
public interface CourseInfoService extends IService<CourseInfo> {
    List<CourseInfo> queryByTid(Integer tid);

    int save(CourseInfoInput courseInfoInput);
}

