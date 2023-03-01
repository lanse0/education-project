package com.qf;

import com.qf.ability.notify.application.NotifyApplication;
import com.qf.ability.notify.server.IEmailService;
import com.qf.data.notify.entity.Email;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * author 14526
 * create_time 2023/3/2
 */
@SpringBootTest(classes = NotifyApplication.class)
@RunWith(SpringRunner.class)
public class TestEmail {

    @Autowired
    private IEmailService emailService;

    @Test
    public void sendEmail(){
        Email email = new Email();
        email.setSubject("发送邮箱测试标题");
        email.setContent("发送邮箱成功123123");
        email.addTo("1452682801@qq.com");
        boolean b = emailService.sendEmail(email);
        if (b){
            System.out.println("发送成功！");
            return;
        }
        System.out.println("发送失败！");
    }

}
