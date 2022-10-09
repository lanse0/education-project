package com.qf.data.user.dto;

import com.qf.data.user.entity.SysPower;
import lombok.Data;

/**
 * author 14526
 * create_time 2022/10/9
 */
@Data
public class SysPowerCheckDto extends SysPower {
    private boolean checked;
}
