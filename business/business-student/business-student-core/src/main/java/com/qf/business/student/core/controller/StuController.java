package com.qf.business.student.core.controller;

import com.qf.business.student.core.service.UserService;
import com.qf.commons.data.result.R;
import com.qf.data.student.entity.User;
import com.qf.data.student.vo.input.UserRegisterInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * author 14526
 * create_time 2022/9/20
 */
@RestController
@RequestMapping("/stu")
@Slf4j
@Validated //在方法上使用的参数校验 不推荐使用
public class StuController {

    //使用lombox 的@Slf4j 简化日志 直接使用log.info 无需getLogger
    //private Logger logger = LoggerFactory.getLogger(StuController.class);

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private UserService userService;

    @RequestMapping("/getById")
    public R getById(Integer id) throws InterruptedException {
        System.out.println("请求了stuController");
//        Thread.sleep(2100);
        User user = userService.getById(id);
        return R.create(user);
    }

    @RequestMapping("/list")
    public R list(Integer a) {

//        log.debug("debug级别的日志");
//        log.info("info级别的日志");
//        log.warn("warn级别的日志");
//        log.error("error级别的日志");

        //发布一个自定义事件 事件发布机制
//        applicationContext.publishEvent(new MyEvent(applicationContext));
        log.debug("查询学生信息");

        List<User> users = userService.list();

        return R.create(users);
    }

    @RequestMapping("/listByCourse")
    public R query(){
        return R.create(userService.queryUserAndCourse());
    }

    /**
     * 保存用户信息
     * @return
     */
    @RequestMapping("/insert")
    public R insert(@Valid UserRegisterInput registerInput){ //@Valid 参数验证注解配合实体类中的规则使用
        log.debug("新增用户信息- {}", registerInput);
        //转换
        User user = new User();
        BeanUtils.copyProperties(registerInput,user); //把输入实体复制到user中
        userService.save(user);
        return R.create("success");
    }

    /**
     * 修改用户信息
     * @return
     */
    @RequestMapping("/update")
    public R update(){
        User user = new User();
        user.setId(2);
        user.setRole(3);
        return R.create(userService.updateById(user));
    }

    @RequestMapping("/reg")
    public R register(@NotBlank(message = "用户名不能为空") String username, @NotBlank(message = "密码不能为空") String password){
        log.debug("用户注册 {} - {}",username,password);
        return R.create("注册成功");
    }
}
