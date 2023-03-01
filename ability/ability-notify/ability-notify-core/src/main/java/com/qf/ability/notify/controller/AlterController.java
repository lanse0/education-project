package com.qf.ability.notify.controller;

import com.alibaba.fastjson.JSON;
import com.qf.ability.notify.server.IEmailService;
import com.qf.commons.data.result.R;
import com.qf.data.notify.entity.AlertMsg;
import com.qf.data.notify.entity.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author 14526
 * create_time 2022/10/13
 */
@RestController
@RequestMapping("/skywalking")
@Slf4j
public class AlterController {

    @Autowired
    private IEmailService emailService;
    @Value("${admin.email}")
    private String adminEmail;

    @RequestMapping("/alert")
    public R emailNotify(@RequestBody List<AlertMsg> alertMsgList) {
        log.debug("[skywalking alert] 接收到skywalking的告警消息 - {}", alertMsgList);

        //把告警信息通过邮件发送出去
        Email email = new Email()
                .setSubject("服务异常告警通知")
                .addTo(adminEmail)
                .setContent(JSON.toJSONString(alertMsgList));
        boolean flag = emailService.sendEmail(email);

        return R.create(flag);
    }
}
