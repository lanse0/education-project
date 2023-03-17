package com.qf.business.user.core.controller;

import com.qf.business.user.core.service.WxUserService;
import com.qf.commons.data.result.R;
import com.qf.data.user.entity.WxUser;
import com.qf.data.user.vo.input.WxUserInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author 14526
 * create_time 2023/3/17
 */
@RestController
@RequestMapping("/wx")
public class WxUserController {

    @Autowired
    private WxUserService wxUserService;

    /**
     * 查询微信的用户信息
     * @param wxUserInput
     * @return
     */
    @RequestMapping("/query")
    public R<WxUser> queryWxUser(@RequestBody WxUserInput wxUserInput){
        WxUser wxUser = wxUserService.login(wxUserInput);

        return R.create(wxUser);
    }
}
