package com.qf.business.redenvlopes.core.controller;

import com.data.redenvlopes.vo.input.RedEnvlopesInput;
import com.qf.business.redenvlopes.core.service.RedEnvelopesService;
import com.qf.commons.data.result.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * author 14526
 * create_time 2023/3/23
 */
@RestController
@RequestMapping("/red")
public class RedEnvlopesController {

    @Autowired
    private RedEnvelopesService redEnvelopesService;

    /**
     * 发送红包
     * @return
     */
    @RequestMapping("/send")
    public R send(@Valid RedEnvlopesInput redEnvlopesInput){
        int redId = redEnvelopesService.sendRed(redEnvlopesInput);
        return R.create(redId);
    }

    /**
     * 抢红包的请求
     * @param redid
     * @return 正数-抢到的积分 -1已抢完 -2红包过期 -3用户已抢过
     */
    @RequestMapping("/rob")
    public R rob(Integer redid){
        //返回值：正数-抢到的积分 -1已抢完 -2红包过期 -3用户已抢过
        int result = redEnvelopesService.robRed(redid);
        return R.create(result);
    }
}
