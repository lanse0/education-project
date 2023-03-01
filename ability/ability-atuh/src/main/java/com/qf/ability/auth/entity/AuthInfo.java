package com.qf.ability.auth.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 认证的参数信息
 * author 14526
 * create_time 2022/10/10
 */
@Data
@Accessors(chain = true)
public class AuthInfo {

    //请求来源 0 后台管理 1 教师端 2 学生
    private Integer fromType;

    private String username;
    private String password;

    private String email;
    private String code;
}
