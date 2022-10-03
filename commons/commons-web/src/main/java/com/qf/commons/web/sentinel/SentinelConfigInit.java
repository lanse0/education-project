package com.qf.commons.web.sentinel;

import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.property.SentinelProperty;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.nacos.api.PropertyKeyConst;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Properties;

/**
 * 从Nacos上读取流控和降级规则
 * author 14526
 * create_time 2022/10/4
 */
//@Import(SentinelExceptionHandler.class) //SentinelConfigInit类被加载时 Sentinel的异常处理类同时也加载 导入
public class SentinelConfigInit implements CommandLineRunner {

    @Value("${spring.application.name}")
    private String name;
    @Value("${spring.cloud.nacos.server-addr}")
    private String nacosServer;

    @Override
    public void run(String... args) throws Exception {
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.SERVER_ADDR, nacosServer);
        properties.setProperty(PropertyKeyConst.NAMESPACE, "sentinel");

        //配置Nacos配置文件地址，获取流控配置
        ReadableDataSource<String, List<FlowRule>> flowDataSource = new NacosDataSource<>(
                properties,
                "DEFAULT_GROUP",
                name + "-flow.json",
                json -> JSON.parseObject(json, new TypeReference<List<FlowRule>>() {
                }));
        //设置当前服务的流控规则
        FlowRuleManager.register2Property(flowDataSource.getProperty());

        //设置nacos配置文件地址，获取降级规则
        ReadableDataSource<String, List<DegradeRule>> degradeDataSource = new NacosDataSource<>(
                properties,
                "DEFAULT_GROUP",
                name + "-degrade.json",
                json -> JSON.parseObject(json, new TypeReference<List<DegradeRule>>() {
                }));
        //将nacos中的降级信息设置到微服务的Sentinel组件中
        DegradeRuleManager.register2Property(degradeDataSource.getProperty());
    }
}
