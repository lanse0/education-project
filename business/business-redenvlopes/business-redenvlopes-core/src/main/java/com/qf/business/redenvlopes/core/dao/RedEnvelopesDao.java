package com.qf.business.redenvlopes.core.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.data.redenvlopes.dto.RedEnvelopesDto;
import org.apache.ibatis.annotations.Param;
import com.data.redenvlopes.entity.RedEnvelopes;

/**
 * 红包表(RedEnvelopes)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-21 23:45:35
 */
public interface RedEnvelopesDao extends BaseMapper<RedEnvelopes> {

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<RedEnvelopes> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<RedEnvelopes> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<RedEnvelopes> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<RedEnvelopes> entities);

    /**
     * 根据红包id  查询出包含剩余积分数和红包数量的dto类
     * @param redid 红包id
     * @return 然会红包dto类 提供给抢红包业务使用，包含剩余积分和剩余红包数属性
     */
    RedEnvelopesDto queryRedById(Integer redid);
}

