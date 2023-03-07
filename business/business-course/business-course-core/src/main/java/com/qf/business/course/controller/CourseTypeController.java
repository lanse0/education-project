package com.qf.business.course.controller;

import com.qf.business.course.service.CourseTypeService;
import com.qf.commons.data.result.R;
import com.qf.data.course.entity.CourseType;
import com.qf.data.course.vo.output.CourseTypeTreeNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author 14526
 * create_time 2023/3/7
 */
@RestController
@RequestMapping("/course/type")
@Slf4j
public class CourseTypeController {

    @Autowired
    private CourseTypeService courseTypeService;

    /**
     * 根据树形结构查询分类的列表
     *
     * @return
     */
    @RequestMapping("/treeList")
    public R queryTreeList() {

        //查询所有课程分类
        List<CourseType> courseTypes = courseTypeService.list();

        //转换成树形结构
        //TreeNode - 一级
        //    children - 二级TreeNode
        //                      children - 三级
        //label: '一级 1',
        //        children: [{
        //                  label: '二级 1-1',
        //                  children: [{
        //                          label: '三级 1-1-1'
        //                  }]
        //        }]
        List<CourseTypeTreeNode> typeTreeNodes = new ArrayList<>();
        //设置一个map集合 方便子节点查找父节点
        Map<Integer, CourseTypeTreeNode> typeTreeNodeMap = new HashMap<>();
        //遍历所有节点
        for (CourseType courseType : courseTypes) {

            CourseTypeTreeNode treeNode = new CourseTypeTreeNode()
                    .setId(courseType.getId())
                    .setLabel(courseType.getTname())
                    .setLevel(courseType.getStatus());
            if (courseType.getPid() == null) {
                //一级分类 添加到list集合中
                typeTreeNodes.add(treeNode);
            } else {
                //二级节点 找到上级节点 把当前节点放入children
                CourseTypeTreeNode parentNode = typeTreeNodeMap.get(courseType.getPid());
                parentNode.addNode(treeNode);
            }
            //放入map集合
            typeTreeNodeMap.put(courseType.getId(), treeNode);
        }

        return R.create(typeTreeNodes);
    }

    /**
     * 新增课程分类
     *
     * @return
     */
    @RequestMapping("/insert")
    public R insert(CourseType courseType) {

        courseTypeService.save(courseType);

        return R.create("succ");
    }
}
