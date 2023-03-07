package com.qf.business.course.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.data.course.entity.CourseTypeGuige;
import org.apache.ibatis.annotations.Param;

/**
 * 课程分类和规格关联表(CourseTypeGuige)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-06 20:14:13
 */
public interface CourseTypeGuigeDao extends BaseMapper<CourseTypeGuige> {

/**
* 批量新增数据（MyBatis原生foreach方法）
*
* @param entities List<CourseTypeGuige> 实例对象列表
* @return 影响行数
*/
int insertBatch(@Param("entities") List<CourseTypeGuige> entities);

/**
* 批量新增或按主键更新数据（MyBatis原生foreach方法）
*
* @param entities List<CourseTypeGuige> 实例对象列表
* @return 影响行数
* @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
*/
int insertOrUpdateBatch(@Param("entities") List<CourseTypeGuige> entities);

}

