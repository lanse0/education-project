package com.qf.commons.data.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
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
    //填充 填充DataMetaUpdate类中配好的value
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    //状态
    @TableField(fill = FieldFill.INSERT)
    private Integer status;
    //删除表示 0可用 1删除
    @TableField(fill = FieldFill.INSERT)
    private Integer delFlag;
}
