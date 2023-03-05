package com.qf.data.user.vo.input;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 教师表(Teacher)表实体类
 *
 * @author makejava
 * @since 2023-03-02 17:53:45
 */
@Data
public class TeacherInput {
    private Integer id;
    //姓名
    @NotBlank(message = "姓名不能为空")
    private String name;
    //性别
    @NotNull(message = "请选择性别")
    private Integer sex;
    //生日
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    //头像
    private String header;
    //个人简介
    private String info;
}

