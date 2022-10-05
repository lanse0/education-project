package com.qf.business.course.tcc;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * author 14526
 * create_time 2022/10/5
 */
@LocalTCC
public interface ICourseTcc {

    //主业务方法
    @TwoPhaseBusinessAction(name = "courseTcc", commitMethod = "commit", rollbackMethod = "rollback")
    String query(@BusinessActionContextParameter(paramName = "cid") Integer cid);

    boolean commit(BusinessActionContext context);

    boolean rollback(BusinessActionContext context);
}
