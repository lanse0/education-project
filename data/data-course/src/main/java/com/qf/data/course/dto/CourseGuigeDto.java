package com.qf.data.course.dto;

import com.qf.data.course.entity.CourseGuige;
import com.qf.data.course.entity.CourseGuigeVal;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * author 14526
 * create_time 2023/3/10
 */
@Data
@ToString(callSuper = true)
public class CourseGuigeDto extends CourseGuige {

    //当前规格下的所有可选值
    private List<CourseGuigeVal> vals;
}
