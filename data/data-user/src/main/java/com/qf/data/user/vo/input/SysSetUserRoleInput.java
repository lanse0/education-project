package com.qf.data.user.vo.input;

import lombok.Data;

import java.util.List;

/**
 * 设置用户角色的入参对象
 * author 14526
 * create_time 2022/10/10
 */
@Data
public class SysSetUserRoleInput {
    private Integer uid;
    //角色集合
    private List<Integer> rids;
}
