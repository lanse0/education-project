package com.qf.data.course.dto;

import com.qf.data.course.entity.CourseInfo;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 用于转换为搜索服务的实体类
 * author 14526
 * create_time 2023/3/15
 */
@Data
@Accessors(chain = true)
public class CourseSearchDto extends CourseInfo implements Serializable {

    private List<Guiges> guiges;

    @Data
    public static class Guiges implements Serializable{
        private Integer gid;
        private Integer valid;
    }
}
