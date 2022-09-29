package com.qf.business.course.controller;

import com.qf.business.student.feign.StuFeign;
import com.qf.commons.data.result.R;
import com.qf.data.student.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
    private StuFeign stuFeign;

    /**
     * 请求课程
     * @return
     */
    @RequestMapping("/query")
    public String query(){
        System.out.println("请求课程的Controller");
        List<User> users = (List<User>) stuFeign.list(1).getData();
        log.info("获取到学生数据---》"+users);
        return "课程信息，"+users;
    }

    @RequestMapping("/getUserById")
    public R getUserById(Integer id){
        R r = stuFeign.getById(id);
        System.out.println(r.getData());
        return r;
    }
}
