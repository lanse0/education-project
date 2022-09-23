package com.qf.data.student.entity;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 课程表(Course)表实体类
 *
 * @author makejava
 * @since 2022-09-23 15:14:00
 */
@Data
public class Course implements Serializable {
    //主键
    private Integer id;
    //课程名称
    private String courseName;
    //授课老师 - user(id)外键
    private Integer tid;
    //课程最大人数
    private Integer courseNumber;
    //最大申请人数
    private Integer appluNumber;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //状态
    private Integer status;
    //删除表示 0可用 1删除
    private Integer delFlag;
}

