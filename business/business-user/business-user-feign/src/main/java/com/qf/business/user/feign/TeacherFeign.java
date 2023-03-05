package com.qf.business.user.feign;

import com.qf.commons.data.result.R;
import com.qf.data.user.entity.Teacher;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * author 14526
 * create_time 2023/3/2
 */
@FeignClient(value = "user-server",contextId = "teacher") //
@RequestMapping("/teacher")
public interface TeacherFeign {

    /**
     * 通过邮箱查询用户信息
     *
     * @param email
     * @return
     */
    @RequestMapping("/query")
    R<Teacher> queryTeacherByEmail(@RequestParam("email") String email);
}
