package com.qf.business.student.controller;

import com.qf.commons.core.exception.ServiceException;
import com.qf.commons.data.result.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author 14526
 * create_time 2022/9/20
 */
@RestController
@RequestMapping("/stu")
public class StuController {

    @RequestMapping("/list")
    public R list(Integer a) {
        if (a<0){
            throw new ServiceException(403,"参数范围不合法");
        }
        return R.create("学生列表");
    }
}
