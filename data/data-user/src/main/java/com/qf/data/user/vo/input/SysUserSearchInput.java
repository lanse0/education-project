package com.qf.data.user.vo.input;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 后台用户搜索入参 VO对象
 * author 14526
 * create_time 2022/10/12
 */
@Data
public class SysUserSearchInput {
    private String keyword;
    private Integer sex;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
}
