package com.qf.data.user.vo.input;

import lombok.Data;

import java.util.List;

/**
 * 设置角色关联权限的vo对象
 * author 14526
 * create_time 2022/10/9
 */
@Data
public class SysSetRolePowerInput {
    //当前角色id
    private Integer rid;
    //权限的id集合
    private List<Integer> pids;
}
