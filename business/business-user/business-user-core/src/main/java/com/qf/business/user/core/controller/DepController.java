package com.qf.business.user.core.controller;

import com.qf.business.user.core.service.SysDepartmentService;
import com.qf.commons.core.utils.QfBeanUtils;
import com.qf.commons.data.base.BaseUser;
import com.qf.commons.data.result.R;
import com.qf.commons.web.aspect.annotation.GetUser;
import com.qf.commons.web.utils.UserUtils;
import com.qf.data.user.entity.SysDepartment;
import com.qf.data.user.vo.input.SysDepartmentInput;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 部门控制的Controller
 * <p>
 * author 14526
 * create_time 2022/10/8
 */
@RestController
@RequestMapping("/sys/dep")
@Slf4j
public class DepController {

    @Autowired
    private SysDepartmentService departmentService;

    /**
     * 查询部门列表
     *
     * @return
     */
    @RequestMapping("/list")
    @GetUser //使用切面获取用户信息
    public R<List<SysDepartment>> list() {

        //在切面类中获取了用户信息 放在了UserUtils里面的ThreadLocal中
        BaseUser user = UserUtils.getUser();
        System.out.println("登录的用户信息："+user);

        List<SysDepartment> deps = departmentService.list();
        return R.create(deps);
    }

    /**
     * 新增部门
     *
     * @return
     */
    @RequestMapping("/insert")
    public R insert(@Valid SysDepartmentInput departmentInput) {
        log.debug("[dep insert] 部门新增 - {}", departmentInput);
        //类型转换 vo -> entity
        SysDepartment sysDepartment = QfBeanUtils.copyBean(departmentInput, SysDepartment.class);
        //调用业务层进行保存
        departmentService.save(sysDepartment);
        return R.create("succ");
    }

    /**
     * 删除部门
     *
     * @param id
     * @return
     */
    @RequestMapping("/removeById")
    public R removeById(@Param("id") Integer id) {
        log.debug("[dep removeById] 删除部门 - {}", id);
        departmentService.removeById(id);
        return R.create("succ");
    }

    /**
     * 修改部门
     *
     * @param departmentInput
     * @return
     */
    @RequestMapping("/update")
    public R update(@Valid SysDepartmentInput departmentInput) {
        log.debug("[dep update] 修改部门 - {}", departmentInput);
        SysDepartment department = QfBeanUtils.copyBean(departmentInput, SysDepartment.class);
        departmentService.updateById(department);
        return R.create("succ");
    }

}
