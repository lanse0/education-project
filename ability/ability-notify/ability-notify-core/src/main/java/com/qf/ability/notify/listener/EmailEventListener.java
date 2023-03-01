package com.qf.ability.notify.listener;

import com.ken.event.action.apply.consumer.IKenEventHandler;
import com.ken.event.action.apply.consumer.KenEvent;
import com.ken.event.standard.entity.KenMessage;
import com.qf.ability.notify.server.IEmailService;
import com.qf.data.notify.entity.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * author 14526
 * create_time 2023/3/1
 */
@Slf4j
@KenEvent("send-email")
public class EmailEventListener implements IKenEventHandler<Email> {

    @Autowired
    private IEmailService emailService;

    @Override
    public void eventHandler(Email email, KenMessage kenMessage) {
        log.debug("[send-email] 接收到发送邮件请求 - {}", email);
        //发送邮箱
        boolean b = emailService.sendEmail(email);
        if (b){
            log.debug("[send-email] 邮箱发送成功！");
            return;
        }
        log.debug("[send-email] 邮箱发送失败！");
    }
}
