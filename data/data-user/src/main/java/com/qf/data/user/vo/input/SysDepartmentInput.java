package com.qf.data.user.vo.input;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * author 14526
 * create_time 2022/10/8
 */
@Data
public class SysDepartmentInput {
    private Integer id;
    @NotBlank(message = "部门名称不能为空")
    private String depName;
    private String depInfo;
}
