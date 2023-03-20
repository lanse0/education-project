package com.qf.business.user.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.business.user.core.dao.WxUserDao;
import com.qf.commons.core.exception.ServiceException;
import com.qf.commons.core.utils.QfBeanUtils;
import com.qf.commons.data.base.BaseUser;
import com.qf.commons.web.aspect.annotation.GetUser;
import com.qf.commons.web.utils.UserUtils;
import com.qf.data.user.entity.WxUser;
import com.qf.business.user.core.service.WxUserService;
import com.qf.data.user.vo.input.WxUserInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 小程序端的用户表(WxUser)表服务实现类
 *
 * @author makejava
 * @since 2023-03-17 21:45:17
 */
@Service("wxUserService")
@Slf4j
public class WxUserServiceImpl extends ServiceImpl<WxUserDao, WxUser> implements WxUserService {

    @Autowired //使用resttemplate 向第三方发送请求
    private RestTemplate restTemplate;

    /**
     * 小程序端登录
     *
     * @param wxUserInput
     * @return
     */
    @Override
    public WxUser login(WxUserInput wxUserInput) {

        //获得前端的code
        String code = wxUserInput.getCode();
        //发送请求给微信服务器，校验code是否正确，并且获取小程序端的相关参数(openid) get请求
        String response = restTemplate.getForObject("https://api.weixin.qq.com/sns/jscode2session?appid=wxa1dbf1e19fb8a5d9&secret=ff03a021d28a6c100d1aa358524438b1&js_code=" + code + "&grant_type=authorization_code", String.class);
        log.debug("[wx login] 小程序端登录 - {}", response);
        JSONObject jsonObject = JSON.parseObject(response);
        Integer errorCode = (Integer) jsonObject.get("errcode"); //微信接口文档返回码 0 请求成功
        if (errorCode != null && errorCode != 0) {
            throw new ServiceException(500, "小程序端登录异常");
        }
        //请求成功 解析json字符串
        String sessionKey = jsonObject.getString("session_key");
        String openid = jsonObject.getString("openid");
        //根据openid查询数据 用户是否已存在
        WxUser wxUser = this.query().eq("openid", openid).one();
        //没有用户信息，进行注册操作
        if (wxUser == null) {
            wxUser = new WxUser()
                    .setOpenid(openid)
                    .setSessionKey(sessionKey)
                    .setHeader(wxUserInput.getHeader())
                    .setNickname(wxUserInput.getNickname());
            //保存用户信息
            this.save(wxUser);
        }

        return wxUser;
    }

    /**
     * 保存用户修改的信息
     *
     * @param wxUserInput
     * @return
     */
    @Override
    @GetUser
    public boolean updateWxUser(WxUserInput wxUserInput) {
        BaseUser user = UserUtils.getUser();
        WxUser wxUser = QfBeanUtils.copyBean(wxUserInput, WxUser.class);
        wxUser.setId(user.getId());
        return this.updateById(wxUser);
    }
}

