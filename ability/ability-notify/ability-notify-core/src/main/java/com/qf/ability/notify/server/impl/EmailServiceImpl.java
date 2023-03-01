package com.qf.ability.notify.server.impl;

import com.qf.ability.notify.server.IEmailService;
import com.qf.data.notify.entity.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

/**
 * author 14526
 * create_time 2022/10/13
 */
@Service
public class EmailServiceImpl implements IEmailService {

    /**
     * 发送邮件的对象
     */
    @Autowired
    private JavaMailSender sender;
    @Value("${spring.mail.username}")
    private String emailFrom;

    /**
     * 发送邮件
     * @return
     */
    @Override
    public boolean sendEmail(Email email) {
        //创建一封邮件
        MimeMessage mimeMessage = sender.createMimeMessage();


        //设置邮件的相关配置
        try {
            //使用spring提供的helper对邮件对象做一个封装  true:支持附件
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);

            //标题
            mimeMessageHelper.setSubject(email.getSubject());
            //发件人
            mimeMessageHelper.setFrom(emailFrom);
            //收件方
            for (String to : email.getTos()) {
                mimeMessageHelper.addTo(to);//普通收件人
            }
            //邮件内容
            mimeMessageHelper.setText(email.getContent());
            //附件
            if (email.getAttrFile()!=null){
                File file = email.getAttrFile();
                mimeMessageHelper.addAttachment(file.getName(),file);
            }

            //邮件发送时间
            mimeMessageHelper.setSentDate(new Date());

            //发送邮件
            sender.send(mimeMessage);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }
}
