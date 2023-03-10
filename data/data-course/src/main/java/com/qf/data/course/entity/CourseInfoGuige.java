package com.qf.data.course.entity;


import com.qf.commons.data.base.BaseEntity;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 课程信息和规格关联表(CourseInfoGuige)表实体类
 *
 * @author makejava
 * @since 2023-03-10 13:52:41
 */
@Data
@ToString(callSuper = true)
@Accessors(chain = true)
public class CourseInfoGuige extends BaseEntity {
    //课程id
    private Integer cid;
    //规格id
    private Integer cgid;
    //规格具体的值的id
    private Integer cgvid;
}

