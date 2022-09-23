package com.qf.data.student.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.qf.commons.data.base.BaseEntity;
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
public class StudentCourse extends BaseEntity {
    //学生id
    @TableId(type = IdType.AUTO)
    private Integer sid;
    //课程id
    private Integer cid;

}

