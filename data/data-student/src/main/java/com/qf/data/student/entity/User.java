package com.qf.data.student.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.qf.commons.data.base.BaseEntity;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
    @NotBlank(message = "用户名不能为空！")
    private String username;
    //密码
    @NotBlank(message = "密码不能为空！")
    private String password;
    //姓名
    @NotBlank(message = "姓名不能为空！")
    @Size(min = 2, max = 5,message = "名字长度不合法！")
    private String name;
    //性别 0男 1女
    @Min(value = 0,message = "性别参数不合法!")
    @Max(value = 1,message = "性别参数不合法!")
    private Integer sex;
    //头像
    private String header;
    //角色 0管理员 1教师 2学生
    private Integer role;
    //课程
    /**
     * 表示当前字段在数据库中不存在 @TableField(exist = false)
     */
    private List<Course> courses;
}

