package com.qf.ability.notify.controller;

import com.qf.commons.data.result.R;
import com.qf.data.notify.entity.AlertMsg;
import lombok.extern.slf4j.Slf4j;
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

    @RequestMapping("/alert")
    public R emailNotify(@RequestBody List<AlertMsg> alertMsgList) {
        log.debug("[skywalking alert] 接收到skywalking的告警消息 - {}", alertMsgList);
        //把告警信息通过邮件发送出去
        return R.create(null);
    }
}
