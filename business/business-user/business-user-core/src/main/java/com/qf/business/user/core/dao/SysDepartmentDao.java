package com.qf.business.user.core.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.data.user.entity.SysDepartment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 部门表(SysDepartment)表数据库访问层
 *
 * @author makejava
 * @since 2022-10-07 14:46:18
 */
public interface SysDepartmentDao extends BaseMapper<SysDepartment> {

/**
* 批量新增数据（MyBatis原生foreach方法）
*
* @param entities List<SysDepartment> 实例对象列表
* @return 影响行数
*/
int insertBatch(@Param("entities") List<SysDepartment> entities);

/**
* 批量新增或按主键更新数据（MyBatis原生foreach方法）
*
* @param entities List<SysDepartment> 实例对象列表
* @return 影响行数
* @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
*/
int insertOrUpdateBatch(@Param("entities") List<SysDepartment> entities);

}

