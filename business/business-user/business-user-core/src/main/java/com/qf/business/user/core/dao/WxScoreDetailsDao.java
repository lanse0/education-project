package com.qf.business.user.core.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.qf.data.user.entity.WxScoreDetails;

/**
 * 积分流水表(WxScoreDetails)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-21 23:40:27
 */
public interface WxScoreDetailsDao extends BaseMapper<WxScoreDetails> {

/**
* 批量新增数据（MyBatis原生foreach方法）
*
* @param entities List<WxScoreDetails> 实例对象列表
* @return 影响行数
*/
int insertBatch(@Param("entities") List<WxScoreDetails> entities);

/**
* 批量新增或按主键更新数据（MyBatis原生foreach方法）
*
* @param entities List<WxScoreDetails> 实例对象列表
* @return 影响行数
* @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
*/
int insertOrUpdateBatch(@Param("entities") List<WxScoreDetails> entities);

}

