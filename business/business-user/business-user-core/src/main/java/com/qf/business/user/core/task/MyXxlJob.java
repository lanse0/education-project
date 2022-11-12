package com.qf.business.user.core.task;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 自定义的分布式定时任务处理器
 * author 14526
 * create_time 2022/11/12
 */
@Component
public class MyXxlJob {

    @XxlJob("myHandler")
    public void handler() {
        System.out.println("XXL-JOB任务已经触发-------" + new Date());

        //分片广播 集群处理任务 每台机器要处理的数据逻辑
        int total = XxlJobHelper.getShardTotal();
        int index = XxlJobHelper.getShardIndex();
        System.out.println("当前机器的下标:" + index + " 总共" + total + "台机器");

        //需要处理的全部数据
        List<Integer> datas = new ArrayList<>();
        for (int i = 1; i <= 10000; i++) {
            datas.add(i);
        }

        int size = datas.size();
        //每台机器需要处理的数量
        int handlerNumber = size / total;
        //当前服务需要处理的数据
        List<Integer> handlerList = null;
        //若当前服务器是最后一台 包揽所有剩余的数据
        if (total - 1 == index){
            //上面计算每台机器处理数量时，可能除不尽 这里直接把最后一条数据包含进来 防止漏数据
            handlerList = datas.subList(index * handlerNumber, size);
        }else{
            handlerList = datas.subList(index * handlerNumber, (index + 1) * handlerNumber);
        }

        System.out.println("当前机器需要处理的数据范围是："+handlerList.get(0)+" - "+handlerList.get(handlerList.size()-1));
    }
}
