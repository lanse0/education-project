package com.qf.business.student.feign;

import com.qf.commons.data.result.R;
import com.qf.data.student.vo.input.UserRegisterInput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * author 14526
 * create_time 2022/9/29
 */
@FeignClient("stu-server")
@RequestMapping("/stu")
public interface StuFeign {

    @RequestMapping("/getById")
    R getById(@RequestParam("id") Integer id);

    @RequestMapping("/list")
    R list(Integer a);

    @RequestMapping("/listByCourse")
    R query();

    /**
     * 保存用户信息
     * @return
     */
    @RequestMapping("/insert")
    R insert(@RequestBody UserRegisterInput registerInput);

    /**
     * 修改用户信息
     * @return
     */
    @RequestMapping("/update")
    R update();

    @RequestMapping("/reg")
    R register(@RequestParam("username") String username,@RequestParam("password") String password);
}
