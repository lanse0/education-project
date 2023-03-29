package com.qf.business.redenvlopes.core.listener;

import com.ken.event.action.apply.consumer.IKenEventHandler;
import com.ken.event.action.apply.consumer.KenEvent;
import com.ken.event.standard.entity.KenMessage;
import com.qf.business.redenvlopes.core.service.RedEnvelopesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * author 14526
 * create_time 2023/3/28
 */
@KenEvent("rob-red") //事件总线 监听网关拦截器发送的rob-red事件
@Slf4j
public class RedRobEventHandler implements IKenEventHandler<Map<String,Integer>> {

    @Autowired
    private RedEnvelopesService redEnvelopesService;

    @Override
    public void eventHandler(Map<String, Integer> map, KenMessage kenMessage) {
        log.debug("[rob red event] 接收到抢红包事件 - {}",map);
        redEnvelopesService.robRed(map.get("redid"),map.get("uid"),map.get("robScore"));
    }
}
