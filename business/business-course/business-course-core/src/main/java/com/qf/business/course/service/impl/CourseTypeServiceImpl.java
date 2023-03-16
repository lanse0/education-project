package com.qf.business.course.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.business.course.dao.CourseTypeDao;
import com.qf.business.course.service.CourseTypeGuigeService;
import com.qf.business.course.service.CourseTypeService;
import com.qf.commons.core.utils.CommonsUtils;
import com.qf.data.course.entity.CourseType;
import com.qf.data.course.entity.CourseTypeGuige;
import com.qf.data.course.vo.input.TypeGuigesInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 课程分类表(CourseType)表服务实现类
 *
 * @author makejava
 * @since 2023-03-06 20:14:13
 */
@Service("courseTypeService")
public class CourseTypeServiceImpl extends ServiceImpl<CourseTypeDao, CourseType> implements CourseTypeService {

    @Autowired
    private CourseTypeGuigeService courseTypeGuigeService;

    /**
     * 插入一条记录（选择字段，策略插入）
     * 插入课程分类 需要先查询父类的等级 计算出当前分类等级 再插入
     *
     * @param entity 实体对象
     */
    @Override
    public boolean save(CourseType entity) {

        //查询父级分类
        CourseType parent = this.getById(entity.getPid());
        if (parent != null) {
            //设置当前分类的flag 这里不设置自身的id 用的时候再拼接 避免过程复杂
            entity.setTag(parent.getTag() != null ? (parent.getTag() + CommonsUtils.id2Str(parent.getId(), 4)) : CommonsUtils.id2Str(parent.getId(), 4));
            //设置当前分类的级别
            entity.setStatus(parent.getStatus() + 1);
        } else {
            //父分类为空 设置当前分类为第一级
            entity.setStatus(1);
        }

        return super.save(entity);
    }

    /**
     * 修改课程类型的规格关联
     *
     * @param typeGuigesInput
     * @return
     */
    @Override
    @Transactional
    public int updateTypeGuiges(TypeGuigesInput typeGuigesInput) {

        //先删除当前课程类型的关联信息
        Map map = new HashMap();
        map.put("tid",typeGuigesInput.getTid());
        courseTypeGuigeService.removeByMap(map);
        //规格列表为空
        if (typeGuigesInput.getGid() == null || typeGuigesInput.getGid().size() == 0) return 1;
        //再插入课程的规格关联信息
        List<CourseTypeGuige> typeGuiges = typeGuigesInput.getGid().stream().map(
                gid -> new CourseTypeGuige().setTid(typeGuigesInput.getTid()).setGid(gid)
        ).collect(Collectors.toList());
        courseTypeGuigeService.saveBatch(typeGuiges);

//        lamboad 表达式 stream流 把int类型的list转换成string类型：
//        List<Integer> a = new ArrayList<>();
//        List<String> stringList = a.stream().map(
//                i -> i + ""
//        ).collect(Collectors.toList());

        return 1;
    }
}
