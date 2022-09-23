package com.qf.commons.data.base;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * author 14526
 * create_time 2022/9/23
 */
@Data
public class BaseEntity implements Serializable {
    //创建时间
    private Date createTime = new Date();
    //更新时间
    private Date updateTime = new Date();
    //状态
    private Integer status = 0;
    //删除表示 0可用 1删除
    private Integer delFlag = 0;
}
