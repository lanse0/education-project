package com.qf.data.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.qf.commons.data.base.BaseEntity;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 部门表(SysDepartment)表实体类
 *
 * @author makejava
 * @since 2022-10-07 14:46:19
 */
@Data
@ToString(callSuper = true)
@Accessors(chain = true)
public class SysDepartment extends BaseEntity {
    //部门主键
    @TableId(type = IdType.AUTO)
    private Integer id;
    //部门名称
    private String depName;
    //部门人数
    private Integer depCount;
    //部门描述
    private String depInfo;
}

