package com.qf.data.student.entity;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 学生选课表(StudentCourse)表实体类
 *
 * @author makejava
 * @since 2022-09-23 15:15:12
 */
@Data
public class StudentCourse implements Serializable {
    //学生id
    private Integer sid;
    //课程id
    private Integer cid;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //状态
    private Integer status;
    //删除表示 0可用 1删除
    private Integer delFlag;

}

