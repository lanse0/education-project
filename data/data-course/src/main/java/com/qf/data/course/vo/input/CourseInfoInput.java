package com.qf.data.course.vo.input;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * author 14526
 * create_time 2023/3/10
 */
@Data
public class CourseInfoInput implements Serializable {

    //课程分类的id - 外键(course_type表id)
    @NotNull(message = "请选择课程分类")
    private Integer tid;
    //课程标题
    @NotBlank(message = "请输入课程标题")
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
    //接收前端传回的的规格对象 包含选中了哪些规格值
    private String guiges;
}
