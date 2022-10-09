package com.qf.business.user.core.controller;

import com.qf.business.user.core.service.SysPowerService;
import com.qf.commons.core.utils.QfBeanUtils;
import com.qf.commons.data.result.R;
import com.qf.data.user.dto.SysPowerCheckDto;
import com.qf.data.user.dto.SysPowerPnameDto;
import com.qf.data.user.entity.SysPower;
import com.qf.data.user.vo.input.SysPowerInput;
import com.qf.data.user.vo.output.SysPowerTreeOutput;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 根据角色id 查询 权限树, 以及当前角色拥有那些权限
     * <p>
     * [
     * {id:1, label:"行政管理", children:[{},{}]},
     * ...
     * ]
     *
     * @return
     */
    @RequestMapping("/queryPowerTreeByRid")
    public R<List<SysPowerTreeOutput>> queryPowerTreeByRid(@Param("rid") Integer rid) {
        //根据角色查询 所有授权以及每条权限的选中情况
        List<SysPowerCheckDto> sysPowerCheckDtos = powerService.queryPowersCheckListByRid(rid);
        //输出结果
        List<SysPowerTreeOutput> powerTrees = new ArrayList<>();

        //准备一个map key-权限id value-权限对应的powerTreeOutput对象
        Map<Integer, SysPowerTreeOutput> powerMap = new HashMap<>();

        //遍历dto集合 转换为权限树输出集合
        for (SysPowerCheckDto dto : sysPowerCheckDtos) {
            //转换dto对象为权限树输出对象
            SysPowerTreeOutput treeOutput = new SysPowerTreeOutput()
                    .setId(dto.getId()).setLabel(dto.getPowerName()).setChecked(dto.isChecked());
            //判断是否为一级权限
            if (dto.getPid() == null) {
                //为一级权限 将权限树对象放入list集合中
                powerTrees.add(treeOutput);
            } else {
                //有父权限，找到父权限并放入到children集合中 用当前元素的父id来找父权限
                SysPowerTreeOutput parentTree = powerMap.get(dto.getPid());
                parentTree.addChildren(treeOutput);
            }
            //将此权限树对象放入map中 用于添加子权限
            powerMap.put(treeOutput.getId(),treeOutput);
        }

        return R.create(powerTrees);
    }
}
