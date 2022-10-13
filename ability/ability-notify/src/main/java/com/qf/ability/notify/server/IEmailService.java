package com.qf.ability.notify.server;

import com.qf.data.notify.entity.Email;

/**
 * author 14526
 * create_time 2022/10/13
 */
public interface IEmailService {
    boolean sendEmail(Email email);
}
