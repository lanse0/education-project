package com.qf.business.course.controller;

import com.qf.business.course.service.CourseInfoService;
import com.qf.commons.data.base.BaseUser;
import com.qf.commons.data.result.R;
import com.qf.commons.web.aspect.annotation.GetUser;
import com.qf.commons.web.utils.UserUtils;
import com.qf.data.course.vo.input.CourseInfoInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * author 14526
 * create_time 2023/3/10
 */
@RestController
@RequestMapping("/course/info")
@Slf4j
public class CourseInfoController {

    @Autowired
    private CourseInfoService courseInfoService;

    /**
     * @return
     */
    @RequestMapping("/listByTid")
    @GetUser
    public R listByTid() {
        BaseUser teacher = UserUtils.getUser();
        log.debug("[listByTId] - 登录的用户：- {}", teacher);
        return R.create(courseInfoService.queryByTid(teacher.getId()));
    }


    /**
     * 新增课程的基本信息
     *
     * @param courseInfoInput
     * @return
     */
    @RequestMapping("/insert")
    public R insert(@Valid CourseInfoInput courseInfoInput) {
        log.debug("[course info insert] 新增课程信息 - {}", courseInfoInput);
        courseInfoService.save(courseInfoInput);
        return R.create("succ");
    }

    /**
     * 根据一级分类查询分类下所有课程
     * @param tid 一级分类id
     * @return
     */
    @RequestMapping("/queryByOneType")
    public R queryByOneType(Integer tid){
        return R.create(courseInfoService.queryByOneType(tid));
    }
}
