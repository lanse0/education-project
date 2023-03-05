package com.qf.business.user.feign;

import com.qf.commons.data.result.R;
import com.qf.data.user.dto.SysUserPowerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * author 14526
 * create_time 2022/10/10
 */
@FeignClient(value = "user-server",contextId = "sysUser")
@RequestMapping("/sys/user")
public interface SysUserFeign {


    @RequestMapping("/queryUserByUn")
    R<SysUserPowerDto> queryUserByUn(@RequestParam("username") String username);
}
