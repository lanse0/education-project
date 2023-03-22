package com.qf.data.user.entity;

import com.qf.commons.data.base.BaseUser;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 小程序端的用户表(WxUser)表实体类
 *
 * @author makejava
 * @since 2023-03-17 21:42:04
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class WxUser extends BaseUser {
    //小程序端的开发id
    private String openid;
    //小程序端的用户在开放平台下的唯一id
    private String unionid;
    //会话密钥
    private String sessionKey;
    //小程序端的用户昵称
    private String nickname;
    //小程序端的头像
    private String header;
    //用户积分(发红包使用)
    private Integer score;
}

