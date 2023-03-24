package com.qf.business.user.core.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.data.user.vo.input.WxScoreUpdateInput;
import org.apache.ibatis.annotations.Param;
import com.qf.data.user.entity.WxUser;

/**
 * 小程序端的用户表(WxUser)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-17 21:45:17
 */
public interface WxUserDao extends BaseMapper<WxUser> {

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<WxUser> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<WxUser> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<WxUser> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<WxUser> entities);

    /**
     * 修改用户积分
     * @param wxScoreUpdateInput
     * @return
     */
    int updateWxScore(WxScoreUpdateInput wxScoreUpdateInput);

}

