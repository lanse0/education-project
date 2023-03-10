package com.qf.business.course.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.qf.data.course.entity.CourseDescInfo;

/**
 * 课程详细计划表(小节)(CourseDescInfo)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-10 13:52:41
 */
public interface CourseDescInfoDao extends BaseMapper<CourseDescInfo> {

/**
* 批量新增数据（MyBatis原生foreach方法）
*
* @param entities List<CourseDescInfo> 实例对象列表
* @return 影响行数
*/
int insertBatch(@Param("entities") List<CourseDescInfo> entities);

/**
* 批量新增或按主键更新数据（MyBatis原生foreach方法）
*
* @param entities List<CourseDescInfo> 实例对象列表
* @return 影响行数
* @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
*/
int insertOrUpdateBatch(@Param("entities") List<CourseDescInfo> entities);

}

