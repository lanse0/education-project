package com.qf.business.student.core.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.data.student.entity.Course;
import org.apache.ibatis.annotations.Param;

/**
 * 课程表(Course)表数据库访问层
 *
 * @author makejava
 * @since 2022-09-23 15:14:00
 */
public interface CourseDao extends BaseMapper<Course> {

/**
* 批量新增数据（MyBatis原生foreach方法）
*
* @param entities List<Course> 实例对象列表
* @return 影响行数
*/
int insertBatch(@Param("entities") List<Course> entities);

/**
* 批量新增或按主键更新数据（MyBatis原生foreach方法）
*
* @param entities List<Course> 实例对象列表
* @return 影响行数
* @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
*/
int insertOrUpdateBatch(@Param("entities") List<Course> entities);

}

