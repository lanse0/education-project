package com.qf.business.user.core.controller;

import com.qf.business.user.core.service.WxScoreDetailsService;
import com.qf.business.user.core.service.WxUserService;
import com.qf.commons.data.result.R;
import com.qf.commons.web.aspect.annotation.GetUser;
import com.qf.commons.web.utils.UserUtils;
import com.qf.data.user.entity.WxScoreDetails;
import com.qf.data.user.entity.WxUser;
import com.qf.data.user.vo.input.WxScoreUpdateInput;
import com.qf.data.user.vo.input.WxUserInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
    @Autowired
    private WxScoreDetailsService wxScoreDetailsService;

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

    /**
     * 保存用户修改的信息
     * @param wxUserInput
     * @return
     */
    @RequestMapping("/updateWxUser")
    public R updateWxUser(WxUserInput wxUserInput){
        wxUserService.updateWxUser(wxUserInput);
        return R.create("succ");
    }

    /**
     * 查询微信小程序用户的积分信息
     * @return
     */
    @RequestMapping("/score/query")
    @GetUser
    public R queryScore(){
        //获取登录用户id
        Integer id = UserUtils.getUser().getId();
        WxUser user = wxUserService.getById(id);
        return R.create(user.getScore());
    }

    /**
     * 积分修改
     * @return
     */
    @RequestMapping("/score/sub")
    public R<Integer> updateWxScore(@RequestBody WxScoreUpdateInput wxScoreUpdateInput){
        int update = wxUserService.updateWxScore(wxScoreUpdateInput);
        return R.create(update);
    }

    /**
     * 生成积分流水(积分流水表)
     * @return
     */
    @RequestMapping("/score/details")
    public R<Integer> createScoreDetails(@RequestBody WxScoreDetails wxScoreDetails){
        wxScoreDetailsService.save(wxScoreDetails);
        //直接返回1 保存不成功自然会报错
        return R.create(1);
    }
}
