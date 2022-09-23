package com.qf.commons.mybatis.meta;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * mybatis提供的填充方法
 * author 14526
 * create_time 2022/9/23
 */
public class DataMetaUpdate implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        metaObject.setValue("createTime", new Date());
        metaObject.setValue("updateTime", new Date());
        metaObject.setValue("status", 0);
        metaObject.setValue("delFlag", 0);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        metaObject.setValue("updateTime",new Date());
    }
}
