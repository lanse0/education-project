package com.qf.business.user.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.data.user.dto.SysUserDeptDto;
import com.qf.data.user.dto.SysUserPowerDto;
import com.qf.data.user.entity.SysUser;
import com.qf.data.user.vo.input.SysUserSearchInput;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统用户表(SysUser)表数据库访问层
 *
 * @author makejava
 * @since 2022-10-07 14:46:22
 */
public interface SysUserDao extends BaseMapper<SysUser> {

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysUser> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysUser> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysUser> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysUser> entities);

    List<SysUserDeptDto> queryUserDeptList();

    SysUserPowerDto queryUserByUn(String username);

    List<SysUser> search(SysUserSearchInput searchInput);
}

