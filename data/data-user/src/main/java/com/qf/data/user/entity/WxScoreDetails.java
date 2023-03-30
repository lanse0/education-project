package com.qf.data.user.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.qf.commons.data.base.BaseEntity;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;


/**
 * 积分流水表(WxScoreDetails)表实体类
 *
 * @author makejava
 * @since 2023-03-21 23:40:27
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class WxScoreDetails extends BaseEntity {
    //主键
    @TableId(type = IdType.AUTO)
    private Integer id;
    //用户id
    private Integer uid;
    //原始积分
    private Integer sourceScore;
    //操作积分
    private Integer actionScore;
    //积分来源 0-发红包 1-抢红包 2-充值 3-活动 4-红包回退 ... 这个东西应该写一个枚举
    private Integer target;
    //业务id
    private Integer busid;
}

