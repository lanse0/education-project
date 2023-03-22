package com.data.redenvlopes.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.qf.commons.data.base.BaseEntity;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;


/**
 * 红包明细表(RedEnvelopesDetails)表实体类
 *
 * @author makejava
 * @since 2023-03-21 23:45:36
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class RedEnvelopesDetails extends BaseEntity {
    //红包明细id
    @TableId(type = IdType.AUTO)
    private Integer id;
    //红包的id
    private Integer redid;
    //领取者id
    private Integer uid;
    //抢到的积分值
    private Integer score;
    //领取类型 0-抢红包 1-红包回退
    private Integer type;
}

