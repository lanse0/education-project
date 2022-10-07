package com.qf.data.user.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.qf.commons.data.base.BaseEntity;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 角色表(SysRole)表实体类
 *
 * @author makejava
 * @since 2022-10-07 14:46:21
 */
@Data
@ToString(callSuper = true)
@Accessors(chain = true)
public class SysRole extends BaseEntity {
    //角色id
    @TableId(type = IdType.AUTO)
    private Integer id;
    //角色名称
    private String roleName;
    //角色标识
    private String roleTag;
    //角色描述
    private String roleInfo;
    //部门id
    private Integer depId;
}