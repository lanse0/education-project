package com.data.redenvlopes.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.qf.commons.data.base.BaseEntity;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 红包表(RedEnvelopes)表实体类
 *
 * @author makejava
 * @since 2023-03-21 23:45:35
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class RedEnvelopes extends BaseEntity {
    //红包id
    @TableId(type = IdType.AUTO)
    private Integer id;
    //发送者id
    private Integer uid;
    //红包的积分总额 - 红包有多少积分
    private Integer scount;
    //红包的份数
    private Integer number;
    //红包的类型 0-固定 1-随机
    private Integer type;
    //红包信息
    private String info;
    //红包过期时间
    private Date timeout;
}

