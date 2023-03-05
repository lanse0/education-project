package com.qf.business.user.core.controller;

import com.qf.business.user.core.service.TeacherService;
import com.qf.commons.data.result.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author 14526
 * create_time 2023/3/2
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    /**
     * 通过邮箱查询用户信息
     *
     * @param email
     * @return
     */
    @RequestMapping("/query")
    public R queryTeacherByEmail(String email) {
        return R.create(teacherService.queryTeacherByEmail(email));
    }   
}
