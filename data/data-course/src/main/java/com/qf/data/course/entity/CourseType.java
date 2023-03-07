package com.qf.data.course.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.qf.commons.data.base.BaseEntity;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 课程分类表(CourseType)表实体类
 *
 * @author makejava
 * @since 2023-03-06 20:14:13
 */
@Data
@ToString(callSuper = true)
@Accessors(chain = true)
public class CourseType extends BaseEntity {
    //主键
    @TableId(type = IdType.AUTO)
    private Integer id;
    //父分类id
    private Integer pid;
    //分类名称
    private String tname;
    //分类标签 存储上面级分类的id 如：00010002
    private String tag;
}

