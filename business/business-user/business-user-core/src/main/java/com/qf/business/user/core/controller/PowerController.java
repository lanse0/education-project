package com.qf.business.user.core.controller;

import com.qf.business.user.core.service.SysPowerService;
import com.qf.commons.core.utils.QfBeanUtils;
import com.qf.commons.data.result.R;
import com.qf.data.user.dto.SysPowerPnameDto;
import com.qf.data.user.entity.SysPower;
import com.qf.data.user.vo.input.SysPowerInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * author 14526
 * create_time 2022/10/8
 */
@RestController
@RequestMapping("/sys/power")
@Slf4j
public class PowerController {

    @Autowired
    private SysPowerService powerService;

    /**
     * 查询所有权限
     *
     * @return
     */
    @RequestMapping("/list")
    public R list() {
        List<SysPowerPnameDto> result = powerService.queryPnameList();
        return R.create(result);
    }

    /**
     * 添加权限
     *
     * @return
     */
    @RequestMapping("/insert")
    public R insert(@Valid SysPowerInput powerInput) {
        log.debug("[power insert] 新增权限 - {}", powerInput);
        SysPower sysPower = QfBeanUtils.copyBean(powerInput, SysPower.class);
        powerService.save(sysPower);
        return R.create("succ");
    }
}
