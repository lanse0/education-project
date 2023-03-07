package com.qf.data.course.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.qf.commons.data.base.BaseEntity;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 具体规格可选项表(CourseGuigeVal)表实体类
 *
 * @author makejava
 * @since 2023-03-06 20:14:13
 */
@Data
@ToString(callSuper = true)
@Accessors(chain = true)
public class CourseGuigeVal extends BaseEntity {
    //主键
    @TableId(type = IdType.AUTO)
    private Integer id;
    //规格id
    private Integer gid;
    //规格值
    private String val;
}

