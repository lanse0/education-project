package com.qf.data.user.entity;


import com.qf.commons.data.base.BaseUser;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 教师表(Teacher)表实体类
 *
 * @author makejava
 * @since 2023-03-02 17:53:45
 */
@Data
@ToString(callSuper = true)
@Accessors(chain = true)
public class Teacher extends BaseUser {
    //教师邮箱
    private String email;
    //姓名
    private String name;
    //性别
    private Integer sex;
    //生日
    private Date birthday;
    //头像
    private String header;
    //个人简介
    private String info;
}

