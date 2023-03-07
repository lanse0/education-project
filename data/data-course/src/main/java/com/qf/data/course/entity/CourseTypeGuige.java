package com.qf.data.course.entity;


import com.qf.commons.data.base.BaseEntity;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 课程分类和规格关联表(CourseTypeGuige)表实体类
 *
 * @author makejava
 * @since 2023-03-06 20:14:13
 */
@Data
@ToString(callSuper = true)
@Accessors(chain = true)
public class CourseTypeGuige extends BaseEntity {
    //课程分类ID
    private Integer tid;
    //分类规格ID
    private Integer gid;
}

