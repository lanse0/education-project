package com.qf.data.course.vo.output;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * 返回给前端的树形结构节点类
 * author 14526
 * create_time 2023/3/7
 */
@Data
@Accessors(chain = true)
public class CourseTypeTreeNode {

    private Integer id;
    private String label;
    private Integer level;//分类等级
    private List<CourseTypeTreeNode> children = new ArrayList<>();

    public CourseTypeTreeNode addNode(CourseTypeTreeNode courseTypeTreeNode){
        children.add(courseTypeTreeNode);
        return this;
    }
}
