package com.qf.business.redenvlopes.core.task;

import com.data.redenvlopes.entity.RedEnvelopes;
import com.qf.business.redenvlopes.core.service.RedEnvelopesService;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * author 14526
 * create_time 2023/4/3
 */
@Component
@Slf4j
public class RedBackTimeTask {

    @Autowired
    private RedEnvelopesService redEnvelopesService;

    @XxlJob("redback")
    public void redBackHandler(){
        //查询所有过期红包
        List<RedEnvelopes> redEnvelopes = redEnvelopesService.queryTimeoutRed();
        log.debug("[red back handler] 过期红包处理 - {}条记录",redEnvelopes.size());
        for (RedEnvelopes redEnvelope : redEnvelopes) {
            redEnvelopesService.redBack(redEnvelope.getId());
        }
        log.debug("[red back handler] 过期红包处理完成");
    }
}
