package com.qf.ability.search.application;

import com.qf.ability.search.service.ICourseIndexSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 微服务启动时触发 创建索引库等初始化操作
 * author 14526
 * create_time 2023/3/15
 */
@Component
public class ApplicationRunning implements CommandLineRunner {

    @Autowired
    private ICourseIndexSearchService courseIndexSearchService;

    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
        courseIndexSearchService.createIndex();
    }
}
