package com.qf.business.user.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ken.mybatis.entity.Page;
import com.qf.business.user.core.dao.SysDepartmentDao;
import com.qf.business.user.core.service.SysDepartmentService;
import com.qf.commons.core.exception.ServiceException;
import com.qf.commons.data.result.RCodes;
import com.qf.data.user.entity.SysDepartment;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 部门表(SysDepartment)表服务实现类
 *
 * @author makejava
 * @since 2022-10-07 14:46:21
 */
@Service("sysDepartmentService")
@Transactional
@CacheConfig(cacheNames = "depcache")
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
        if (count > 0) {
            throw new ServiceException(RCodes.DATA_UNIQUE_ERROR);
        }
        return super.save(entity);
    }

    /**
     * 查询所有
     */
    @Override
    //使用缓存 调用此方法前 先查询缓存mycache是否有deplist 存在则直接返回 不会执行此方法 否则会调用此方法并通过key重建到缓存池
    //@Cacheable(cacheNames = "depcache",key = "'deplist:'+#page.pageNum+':'+#page.pageSize")
    //@Cacheable(key = "'deplist'")
    public List<SysDepartment> list() {
        System.out.println("查询了数据库");
        return super.list();
    }

    /**
     * ken-page分页插件无法整合redis缓存  总页码和总记录数需要查询了数据库才能获取 使用缓存时 插件的代码里面无法获取到这两个值
     * 后续需更改为其他插件
     * @param page
     * @return

    @Override
    @Cacheable(key = "'deplist:'+#page.pageNum+':'+#page.pageSize")
    public List<SysDepartment> list(Page page) {
        System.out.println("查询了数据库");
        return super.list();
    }*/
}

