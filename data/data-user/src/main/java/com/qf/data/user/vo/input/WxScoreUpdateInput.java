package com.qf.data.user.vo.input;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * author 14526
 * create_time 2023/3/23
 */
@Data
@Accessors(chain = true)
public class WxScoreUpdateInput {

    //需要操作的积分值
    private Integer score;
    //操作的类型 0-扣减 1-增加
    private Integer type;
    //操作的用户id
    private Integer uid;
}
