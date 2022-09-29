package com.qf.data.student.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.qf.commons.data.base.BaseEntity;
import com.qf.data.student.valid.MyValid;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * (User)表实体类
 *
 * @author makejava
 * @since 2022-09-23 15:12:47
 */
@Data
@ToString(callSuper = true) //打印父类的属性
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

    //课程
    @TableField(exist = false) //表示当前字段在数据库中不存在
    private List<Course> courses;
}

