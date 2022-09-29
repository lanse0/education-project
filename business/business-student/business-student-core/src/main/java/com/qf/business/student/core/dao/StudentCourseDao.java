package com.qf.business.student.core.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.data.student.entity.StudentCourse;
import org.apache.ibatis.annotations.Param;

/**
 * 学生选课表(StudentCourse)表数据库访问层
 *
 * @author makejava
 * @since 2022-09-23 15:15:12
 */
public interface StudentCourseDao extends BaseMapper<StudentCourse> {

/**
* 批量新增数据（MyBatis原生foreach方法）
*
* @param entities List<StudentCourse> 实例对象列表
* @return 影响行数
*/
int insertBatch(@Param("entities") List<StudentCourse> entities);

/**
* 批量新增或按主键更新数据（MyBatis原生foreach方法）
*
* @param entities List<StudentCourse> 实例对象列表
* @return 影响行数
* @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
*/
int insertOrUpdateBatch(@Param("entities") List<StudentCourse> entities);

}

