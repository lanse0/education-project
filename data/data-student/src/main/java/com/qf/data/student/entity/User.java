package com.qf.data.student.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.qf.commons.data.base.BaseEntity;
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
public class User extends BaseEntity {
    //主键
    //这个注解表示id自动增长 若未使用这个注解 mybatis-plus会用雪花算法来确定id（分库分表）
    @TableId(type = IdType.AUTO)
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

}

