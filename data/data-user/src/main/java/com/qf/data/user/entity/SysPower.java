package com.qf.data.user.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.qf.commons.data.base.BaseEntity;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;


/**
 * 权限表(SysPower)表实体类
 *
 * @author makejava
 * @since 2022-10-07 14:46:21
 */
@Data
@ToString(callSuper = true)
@Accessors(chain = true)
public class SysPower extends BaseEntity {
    //权限id
    @TableId(type = IdType.AUTO)
    private Integer id;
    //父权限(模块)的id
    private Integer pid;
    //权限名称
    private String powerName;
    //权限的资源路径
    private String powerRes;
    //权限(模块)的类型(级别) 0-一级权限 1-二级权限 2-三级权限
    private Integer powerType;
    //权限的额外信息
    private String powerInfo;
}

