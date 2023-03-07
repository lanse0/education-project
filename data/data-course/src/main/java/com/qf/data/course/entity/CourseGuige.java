package com.qf.data.course.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.qf.commons.data.base.BaseEntity;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 课程分类的规格表(CourseGuige)表实体类
 *
 * @author makejava
 * @since 2023-03-06 20:14:10
 */
@Data
@ToString(callSuper = true)
@Accessors(chain = true)
public class CourseGuige extends BaseEntity {
    //主键
    @TableId(type = IdType.AUTO)
    private Integer id;
    //规格名称
    private String gname;
    //规格备注
    private String info;
}

