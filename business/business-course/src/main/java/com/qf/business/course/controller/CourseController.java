package com.qf.business.course.controller;

import com.qf.business.student.feign.StuFeign;
import com.qf.commons.data.result.R;
import com.qf.data.student.entity.User;
import com.qf.data.student.vo.input.UserRegisterInput;
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
    private StuFeign stuFeign;

    /**
     * 请求课程
     *
     * @return
     */
    @RequestMapping("/query")
    public String query() {
//        System.out.println("请求课程的Controller");
        log.debug("请求课程的Controller");
//        List<User> users = (List<User>) stuFeign.list(1).getData();
        //请求学生
        R result = stuFeign.list(1);
        System.out.println("获取到学生数据---》" + result);
        return "课程信息，" + result;
    }

    @RequestMapping("/getUserById")
    public R getUserById(Integer id) {
        R r = stuFeign.getById(id);
        System.out.println(r.getData());
        return r;
    }

    /**
     * 插入学生测试
     * @return
     */
    @RequestMapping("/insertStu")
    @Transactional
    public String insertStu() {
        UserRegisterInput userRegisterInput = new UserRegisterInput();
        userRegisterInput.setUsername("test");
        userRegisterInput.setPassword("134");
        userRegisterInput.setName("测试");
        userRegisterInput.setAge(0);
        userRegisterInput.setSex(0);
        userRegisterInput.setRole(0);
        R rs = stuFeign.insert(userRegisterInput);
        System.out.println(1/0);
        return "插入结果:" + rs;
    }
}
