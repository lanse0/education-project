package com.qf.data.student.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.qf.commons.data.base.BaseEntity;
import lombok.Data;


/**
 * 课程表(Course)表实体类
 *
 * @author makejava
 * @since 2022-09-23 15:14:00
 */
@Data
public class Course extends BaseEntity {
    //主键
    @TableId(type = IdType.AUTO)
    private Integer id;
    //课程名称
    private String courseName;
    //授课老师 - user(id)外键
    private Integer tid;
    //课程最大人数
    private Integer courseNumber;
    //最大申请人数
    private Integer applyNumber;

}

