package com.qf.ability.auth.controller;

import com.ken.event.action.apply.producer.EventTemplate;
import com.qf.commons.data.result.R;
import com.qf.data.notify.entity.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 处理验证码的controller
 * author 14526
 * create_time 2023/3/1
 */
@RestController
@RequestMapping("/auth/code")
@Slf4j
public class CodeController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 给指定的邮箱发送验证码
     *
     * @return
     */
    @RequestMapping("/email")
    public R sendEmailCode(String email) {
        log.debug("[send email code] 给邮箱发送验证码：- {}", email);

        //生成验证码
        int code = createCode();

        Email sendEmail = new Email();
        sendEmail.setSubject("[登录验证码] - 在线教育");
        sendEmail.setContent("本次登录的动态验证码：" + code + " 5分钟有效，若不是本人操作请忽略。");
        sendEmail.addTo(email);

        //事件总线 -》 通知notify服务发送邮件
        EventTemplate.sendQuickly("send-email", sendEmail);

        //将验证码存放到redis   :: 两个冒号 在一些工具中会识别成文件夹
        stringRedisTemplate.opsForValue().set("code::" + email, code + "", 5, TimeUnit.MINUTES);

        return R.create("succ");
    }

    /**
     * 生成验证码
     *
     * @return
     */
    private int createCode() {
        //1000~9999
        return (int) (Math.random() * 9000) + 1000;//[0-8999] + 1000
    }
}
