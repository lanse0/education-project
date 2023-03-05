package com.qf.business.user.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.data.user.entity.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 教师表(Teacher)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-02 17:53:45
 */
public interface TeacherDao extends BaseMapper<Teacher> {

/**
* 批量新增数据（MyBatis原生foreach方法）
*
* @param entities List<Teacher> 实例对象列表
* @return 影响行数
*/
int insertBatch(@Param("entities") List<Teacher> entities);

/**
* 批量新增或按主键更新数据（MyBatis原生foreach方法）
*
* @param entities List<Teacher> 实例对象列表
* @return 影响行数
* @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
*/
int insertOrUpdateBatch(@Param("entities") List<Teacher> entities);

}

