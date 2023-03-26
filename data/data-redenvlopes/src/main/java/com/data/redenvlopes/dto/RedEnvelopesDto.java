package com.data.redenvlopes.dto;

import com.data.redenvlopes.entity.RedEnvelopes;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * author 14526
 * create_time 2023/3/25
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class RedEnvelopesDto extends RedEnvelopes {
    //剩余的积分数
    private Integer hasScore;
    //剩余的红包数
    private Integer hasCount;
}
