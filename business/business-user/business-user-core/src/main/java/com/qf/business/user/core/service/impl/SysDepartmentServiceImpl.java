package com.qf.business.user.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.business.user.core.dao.SysDepartmentDao;
import com.qf.business.user.core.service.SysDepartmentService;
import com.qf.data.user.entity.SysDepartment;
import org.springframework.stereotype.Service;

/**
 * 部门表(SysDepartment)表服务实现类
 *
 * @author makejava
 * @since 2022-10-07 14:46:21
 */
@Service("sysDepartmentService")
public class SysDepartmentServiceImpl extends ServiceImpl<SysDepartmentDao, SysDepartment> implements SysDepartmentService {

}

