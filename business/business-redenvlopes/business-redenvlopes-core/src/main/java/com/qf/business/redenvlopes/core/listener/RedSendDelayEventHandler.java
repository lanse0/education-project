package com.qf.business.redenvlopes.core.listener;

import com.ken.event.action.apply.consumer.IKenEventHandler;
import com.ken.event.action.apply.consumer.KenEvent;
import com.ken.event.standard.entity.KenMessage;
import com.qf.business.redenvlopes.core.service.RedEnvelopesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 监听红包回退消息
 * author 14526
 * create_time 2023/3/29
 */
@KenEvent("red-send") //red-send 需要抽离出来 用常量
@Slf4j
public class RedSendDelayEventHandler implements IKenEventHandler<Integer> {

    @Autowired
    private RedEnvelopesService redEnvelopesService;

    @Override
    public void eventHandler(Integer redId, KenMessage kenMessage) {
        log.debug("[red timeout] 红包到期的消息 - {}", redId);
        redEnvelopesService.redBack(redId);
    }
}
