package com.qf.data.notify.entity;

import lombok.Data;

/**
 * skywalking告警json信息实体类
 * author 14526
 * create_time 2022/10/13
 */
@Data
public class AlertMsg {
    private Integer scopeId;
    private String scope;//警告类型 服务类型的警告或者接口类型的警告
    private String name;//告警的服务名称或者接口名称
    private String ruleName;//规则名称
    private String alarmMessage;//告警的消息内容
    private long startTime;//告警时间
}
