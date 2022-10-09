package com.qf.data.user.vo.output;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限树的输出实体
 * author 14526
 * create_time 2022/10/9
 */
@Data
@Accessors(chain = true)
public class SysPowerTreeOutput {
    //权限id
    private Integer id;
    //权限名称
    private String label;
    //子权限集合
    private List<SysPowerTreeOutput> children = new ArrayList<>();
    //是否拥有当前权限
    private boolean checked;

    //添加子元素方法
    public void addChildren(SysPowerTreeOutput treeOutput){
        children.add(treeOutput);
    }
}
