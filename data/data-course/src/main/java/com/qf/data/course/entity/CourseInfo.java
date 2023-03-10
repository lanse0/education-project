package com.qf.data.course.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.qf.commons.data.base.BaseEntity;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 课程信息表(CourseInfo)表实体类
 *
 * @author makejava
 * @since 2023-03-10 13:52:41
 */
@Data
@ToString(callSuper = true)
@Accessors(chain = true)
public class CourseInfo extends BaseEntity {
    //主键
    @TableId(type = IdType.AUTO)
    private Integer id;
    //申请教师的ID - 外键(teacher表id)
    private Integer teacherId;
    //课程分类的id - 外键(course_type表id)
    private Integer tid;
    //课程标题
    private String subject;
    //课程封面
    private String fengmian;
    //课程简介
    private String info;
    //课程金额 -1 为免费
    private Double price;
    //课程开始i时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;
    //课程结束时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
}

