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
 * 课程详细计划表(小节)(CourseDescInfo)表实体类
 *
 * @author makejava
 * @since 2023-03-10 13:52:41
 */
@Data
@ToString(callSuper = true)
@Accessors(chain = true)
public class CourseDescInfo extends BaseEntity {
    //主键
    @TableId(type = IdType.AUTO)
    private Integer id;
    //所属课程的外键
    private Integer cid;
    //课程详情标题
    private String title;
    //课程开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime;
    //课程结束时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    //章节类型 0-收费 1-免费试听
    private Integer type;
}

