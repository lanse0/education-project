package com.qf.business.course.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.business.course.dao.CourseDescInfoDao;
import com.qf.data.course.entity.CourseDescInfo;
import com.qf.business.course.service.CourseDescInfoService;
import org.springframework.stereotype.Service;

/**
 * 课程详细计划表(小节)(CourseDescInfo)表服务实现类
 *
 * @author makejava
 * @since 2023-03-10 13:52:41
 */
@Service("courseDescInfoService")
public class CourseDescInfoServiceImpl extends ServiceImpl<CourseDescInfoDao, CourseDescInfo> implements CourseDescInfoService {

}

