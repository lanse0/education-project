package com.qf.business.user.core.controller;

import com.qf.business.user.core.service.TeacherService;
import com.qf.commons.core.utils.QfBeanUtils;
import com.qf.commons.data.result.R;
import com.qf.data.user.entity.Teacher;
import com.qf.data.user.vo.input.TeacherInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * author 14526
 * create_time 2023/3/2
 */
@RestController
@RequestMapping("/teacher")
@Slf4j
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

    /**
     * 修改教师信息
     *
     * @return
     */
    @RequestMapping("/update")
    public R updateTeacherInfo(@Valid TeacherInput teacherInput) {
        log.debug("[update teacher] 修改教师信息 - {}", teacherInput);
        Teacher teacher = QfBeanUtils.copyBean(teacherInput, Teacher.class);
        teacherService.updateById(teacher);

        //更新了用户信息，需要刷新令牌

        return R.create("success");
    }
}
