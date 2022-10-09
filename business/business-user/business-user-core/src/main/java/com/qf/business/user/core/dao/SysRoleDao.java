package com.qf.business.user.core.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.data.user.dto.SysRoleDeptDto;
import com.qf.data.user.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色表(SysRole)表数据库访问层
 *
 * @author makejava
 * @since 2022-10-07 14:46:21
 */
public interface SysRoleDao extends BaseMapper<SysRole> {

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysRole> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysRole> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysRole> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysRole> entities);

    List<SysRoleDeptDto> queryRoleDeptList();
}

