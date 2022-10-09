package com.qf.business.user.core.controller;

import com.qf.business.user.core.service.SysRoleService;
import com.qf.commons.core.utils.QfBeanUtils;
import com.qf.commons.data.result.R;
import com.qf.data.user.entity.SysRole;
import com.qf.data.user.vo.input.SysRoleInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * author 14526
 * create_time 2022/10/9
 */
@RestController
@RequestMapping("/sys/role")
@Slf4j
public class RoleController {

    @Autowired
    private SysRoleService roleService;

    /**
     * 请求角色列表
     *
     * @return
     */
    @RequestMapping("/list")
    public R list() {
        return R.create(roleService.queryRoleDeptList());
    }

    /**
     * 新增角色
     * @return
     */
    @RequestMapping("/insert")
    public R insert(@Valid SysRoleInput roleInput){
        log.debug("[role insert] 角色新增 - {}",roleInput);
        SysRole sysRole = QfBeanUtils.copyBean(roleInput, SysRole.class);
        roleService.save(sysRole);
        return R.create("succ");
    }
}
