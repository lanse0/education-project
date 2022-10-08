package com.qf.business.user.core.controller;

import com.qf.business.user.core.service.SysDepartmentService;
import com.qf.commons.data.result.R;
import com.qf.data.user.entity.SysDepartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 部门控制的Controller
 *
 * author 14526
 * create_time 2022/10/8
 */
@RestController
@RequestMapping("/sys/dep")
public class DepController {

    @Autowired
    private SysDepartmentService departmentService;

    /**
     * 查询部门列表
     * @return
     */
    @RequestMapping("/list")
    public R<List<SysDepartment>> list(){
        List<SysDepartment> deps = departmentService.list();

        return R.create(deps);
    }
}
