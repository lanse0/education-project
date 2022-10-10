package com.qf.data.user.entity;


import com.qf.commons.data.base.BaseUser;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 系统用户表(SysUser)表实体类
 *
 * @author makejava
 * @since 2022-10-07 14:46:22
 */
@Data
@ToString(callSuper = true)
@Accessors(chain = true)
public class SysUser extends BaseUser {
    //用户名
    private String username;
    //密码
    private String password;
    //姓名
    private String name;
    //部门id
    private Integer depId;
    //性别 0-男 1-女
    private Integer sex;

    public SysUser(){
        //设置用户类型
        this.setFromType(0);
    }
}