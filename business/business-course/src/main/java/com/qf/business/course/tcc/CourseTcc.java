package com.qf.business.course.tcc;


import io.seata.rm.tcc.api.BusinessActionContext;
import org.springframework.stereotype.Component;

/**
 * author 14526
 * create_time 2022/10/5
 */
@Component
public class CourseTcc implements ICourseTcc {
    @Override
    public String query(Integer cid) {
        System.out.println("Tcc的核心业务方法");
        return "核心数据";
    }

    @Override
    public boolean commit(BusinessActionContext context) {
        System.out.println("Tcc的提交逻辑" + context.getActionContext("cid"));
        return true;
    }

    @Override
    public boolean rollback(BusinessActionContext context) {
        System.out.println("Tcc的回滚逻辑" + context.getActionContext("cid"));
        return true;
    }
}
