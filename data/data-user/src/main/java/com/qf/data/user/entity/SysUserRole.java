package com.qf.data.user.entity;


import com.qf.commons.data.base.BaseEntity;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 用户和角色的中间表(SysUserRole)表实体类
 *
 * @author makejava
 * @since 2022-10-07 14:46:22
 */

@Data
@ToString(callSuper = true) //callSuper:打印父类的字段
@Accessors(chain = true)
public class SysUserRole extends BaseEntity {
    //系统用户id
    private Integer uid;
    //角色id
    private Integer rid;
}

