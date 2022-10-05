package com.qf.business.course.controller;

import com.qf.business.course.service.ICourseService;
import com.qf.business.student.feign.StuFeign;
import com.qf.commons.data.result.R;
import com.qf.data.student.entity.User;
import com.qf.data.student.vo.input.UserRegisterInput;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author 14526
 * create_time 2022/9/29
 */
@RestController
@RequestMapping("/course")
@Slf4j
public class CourseController {

    @Autowired
    private ICourseService courseService;

    /**
     * 请求课程
     * @return
     */
    @RequestMapping("/query")
    public String query() {
        log.debug("查询课程信息=================");
        return courseService.query();
    }

    @RequestMapping("/getUserById")
    public R getUserById(Integer id) {
        return courseService.getUserById(id);
    }

    /**
     * seata分布式事务测试
     * @return
     */
    @RequestMapping("/insertStu")
    public String insertStu() {
        return courseService.insertStu();
    }
}
