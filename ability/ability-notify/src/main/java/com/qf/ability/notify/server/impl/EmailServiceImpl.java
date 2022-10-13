package com.qf.ability.notify.server.impl;

import com.qf.ability.notify.server.IEmailService;
import org.springframework.stereotype.Service;

/**
 * author 14526
 * create_time 2022/10/13
 */
@Service
public class EmailServiceImpl implements IEmailService {
    /**
     * 发送邮件
     * @return
     */
    @Override
    public boolean sendEmail() {
        return false;
    }
}
