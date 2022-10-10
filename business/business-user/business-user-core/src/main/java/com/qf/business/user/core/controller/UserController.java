package com.qf.business.user.core.controller;

import com.qf.business.user.core.service.SysUserService;
import com.qf.commons.core.utils.QfBeanUtils;
import com.qf.commons.data.result.R;
import com.qf.data.user.dto.SysUserPowerDto;
import com.qf.data.user.entity.SysUser;
import com.qf.data.user.vo.input.SysSetUserRoleInput;
import com.qf.data.user.vo.input.SysUserInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 后台用户管理
 * author 14526
 * create_time 2022/10/9
 */
@RestController
@RequestMapping("/sys/user")
@Slf4j
public class UserController {

    @Autowired
    private SysUserService userService;

    /**
     * 查询后台用户列表
     *
     * @return
     */
    @RequestMapping("/list")
    public R list() {
        return R.create(userService.queryUserDeptList());
    }

    /**
     * 新增用户
     *
     * @return
     */
    @RequestMapping("/insert")
    public R insert(@Valid SysUserInput userInput) {
        log.debug("[insert user] 新增用户 - {}", userInput);
        SysUser user = QfBeanUtils.copyBean(userInput, SysUser.class);
        userService.save(user);
        return R.create("succ");
    }

    /**
     * 根据用户id 修改该用户角色
     * @return
     */
    @RequestMapping("/updateUserRoles")
    public R updateUserRoles(SysSetUserRoleInput userRoleInput){
        log.debug("[set user role] 设置用户角色 - {}",userRoleInput);
        userService.updateUserRoles(userRoleInput);
        return R.create("succ");
    }

    /**
     * 根据用户名查询后台用户的信息
     * @return
     */
    @RequestMapping("/queryUserByUn")
    public R<SysUserPowerDto> queryUserByUn(String username){
        log.debug("[query user byusername] 根据用户名查询用户信息 - {}",username);
        SysUserPowerDto userPowerDto = userService.queryUserByUn(username);
        log.debug("[query user byusername] 查询结果 - {}",userPowerDto);
        return R.create(userPowerDto);
    }
}
