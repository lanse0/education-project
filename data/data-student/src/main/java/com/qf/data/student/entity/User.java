package com.qf.data.student.entity;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (User)表实体类
 *
 * @author makejava
 * @since 2022-09-23 15:12:47
 */
@Data
public class User implements Serializable {
    //主键
    private Integer id;
    //用户名
    private String username;
    //密码
    private String password;
    //姓名
    private String name;
    //性别 0男 1女
    private Integer sex;
    //头像
    private String header;
    //角色 0管理员 1教师 2学生
    private Integer role;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //状态
    private Integer status;
    //删除标识 0可用 1删除
    private Integer delFlag;

}

