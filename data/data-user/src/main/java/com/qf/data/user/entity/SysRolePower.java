package com.qf.data.user.entity;


import com.qf.commons.data.base.BaseEntity;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 角色和权限的中间表(SysRolePower)表实体类
 *
 * @author makejava
 * @since 2022-10-07 15:56:34
 */
@Data
@ToString(callSuper = true)
@Accessors(chain = true)
public class SysRolePower extends BaseEntity {
    //系统用户id
    private Integer rid;
    //角色id
    private Integer powid;
}