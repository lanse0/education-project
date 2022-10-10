package com.qf.commons.data.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * author 14526
 * create_time 2022/10/10
 */
@Data
public class BaseUser extends BaseEntity {

    //主键
    @TableId(type = IdType.AUTO)
    private Integer id;
    //类型(用户端类型) - 0系统用户 1教师端用户 2学生端用户
    private Integer fromType;

}
