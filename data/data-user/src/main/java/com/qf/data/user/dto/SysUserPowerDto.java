package com.qf.data.user.dto;

import com.qf.data.user.entity.SysPower;
import com.qf.data.user.entity.SysUser;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 用户信息 以及 用户下的权限信息的dto
 * author 14526
 * create_time 2022/10/10
 */
@Data
@ToString(callSuper = true)
@Slf4j
public class SysUserPowerDto extends SysUser {

    //权限列表
    private List<SysPower> powers;


    /**
     * 子类重写权限校验方法
     *
     * @param requestUrl
     * @return
     */
    @Override
    public boolean hasPowers(String requestUrl) {
        log.debug("[sys power] 后台权限系统的权限校验 - {}", requestUrl);
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        if (CollectionUtils.isEmpty(powers)) {
            return false;
        }
        //遍历权限列表
        for (SysPower power : powers) {
            //资源路径不为空 且url包含在拥有权限的资源中 返回true
            if (!StringUtils.isEmpty(power.getPowerRes()) && antPathMatcher.match(power.getPowerRes(), requestUrl)) {
                log.debug("[sys power] 权限校验通过 - 拥有的资源：{} 请求的资源：{}", power.getPowerRes(), requestUrl);
                return true;
            }
        }
        log.debug("[sys power] 权限校验不通过 - 请求的资源：{}", requestUrl);
        return false;
    }
}
