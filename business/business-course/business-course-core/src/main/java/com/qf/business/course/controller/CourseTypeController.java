package com.qf.business.course.controller;

import com.qf.business.course.service.CourseTypeGuigeService;
import com.qf.business.course.service.CourseTypeService;
import com.qf.commons.data.result.R;
import com.qf.data.course.dto.CourseGuigeDto;
import com.qf.data.course.entity.CourseType;
import com.qf.data.course.entity.CourseTypeGuige;
import com.qf.data.course.vo.input.TypeGuigesInput;
import com.qf.data.course.vo.output.CourseTypeTreeNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    private CourseTypeGuigeService typeGuigeService;

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

    /**
     * 修改课程分类和 规格的关联关系
     *
     * @return
     */
    @RequestMapping("/updateGuiges")
    public R updateGuiges(TypeGuigesInput input) {
        log.debug("[update type guiges] 设置分类的规格信息 - {} ", input);
        courseTypeService.updateTypeGuiges(input);
        return R.create(null);
    }

    /**
     * 查询课程拥有的规格列表
     *
     * @param tid
     * @return
     */
    @RequestMapping("/guigeSelected")
    public R guigeSelected(Integer tid) {
        //前端只需要id 用stream流将查出来的对象list转换为只有id的list
        List<Integer> tids = typeGuigeService.query().eq("tid", tid).list().stream().map(
                typeGuige -> typeGuige.getGid()
        ).collect(Collectors.toList());

        return R.create(tids);
    }

    /**
     * 查询课程类型拥有的规格列表
     *
     * @param tid
     * @return
     */
    @RequestMapping("/guigeListSelected")
    public R guigeListSelected(Integer tid) {
        List<CourseGuigeDto> courseGuigeDtos = typeGuigeService.queryGuigesByTid(tid);
        log.debug("[query course guiges] 查询指定课程的规格列表 - {}",courseGuigeDtos);
        return R.create(courseGuigeDtos);
    }
}
