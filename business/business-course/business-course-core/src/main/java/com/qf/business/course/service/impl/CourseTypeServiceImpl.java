package com.qf.business.course.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.business.course.dao.CourseTypeDao;
import com.qf.business.course.service.CourseTypeService;
import com.qf.commons.core.utils.CommonsUtils;
import com.qf.data.course.entity.CourseType;
import org.springframework.stereotype.Service;

/**
 * 课程分类表(CourseType)表服务实现类
 *
 * @author makejava
 * @since 2023-03-06 20:14:13
 */
@Service("courseTypeService")
public class CourseTypeServiceImpl extends ServiceImpl<CourseTypeDao, CourseType> implements CourseTypeService {

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
        if (parent!=null){
            //设置当前分类的flag 这里不设置自身的id 用的时候再拼接 避免过程复杂
            entity.setTag(parent.getTag() != null ? (parent.getTag() + CommonsUtils.id2Str(parent.getId(), 4)) : CommonsUtils.id2Str(parent.getId(), 4));
            //设置当前分类的级别
            entity.setStatus(parent.getStatus() + 1);
        }else {
            entity.setStatus(1);
        }

        return super.save(entity);
    }
}
