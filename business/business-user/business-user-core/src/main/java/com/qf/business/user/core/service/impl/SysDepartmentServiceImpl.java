package com.qf.business.user.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.business.user.core.dao.SysDepartmentDao;
import com.qf.business.user.core.service.SysDepartmentService;
import com.qf.commons.core.exception.ServiceException;
import com.qf.commons.data.result.RCodes;
import com.qf.data.user.entity.SysDepartment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 部门表(SysDepartment)表服务实现类
 *
 * @author makejava
 * @since 2022-10-07 14:46:21
 */
@Service("sysDepartmentService")
@Transactional
public class SysDepartmentServiceImpl extends ServiceImpl<SysDepartmentDao, SysDepartment> implements SysDepartmentService {

    /**
     * 保存部门
     *
     * @param entity 实体对象
     */
    @Override
    public boolean save(SysDepartment entity) {
        //判断部门是否存在
        Long count = this.query().eq("dep_name", entity.getDepName()).count();
        if (count>0){
            throw new ServiceException(RCodes.DATA_UNIQUE_ERROR);
        }
        return super.save(entity);
    }
}

