package com.qf.ability.search.service.impl;

import com.qf.ability.search.entity.SearchCourseEntity;
import com.qf.ability.search.service.ICourseIndexSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.stereotype.Service;

/**
 * author 14526
 * create_time 2023/3/15
 */
@Service
@Slf4j
public class CourseIndexSearchServiceImpl implements ICourseIndexSearchService {

    @Autowired
    private ElasticsearchRestTemplate restTemplate;

    /**
     * 创建索引库
     *
     * @return
     */
    @Override
    public boolean createIndex() {
        IndexOperations indexOperations = restTemplate.indexOps(SearchCourseEntity.class);
        //判断索引库是否存在 不存在则执行创建索引
        if (!indexOperations.exists()){
            log.debug("[create index] 课程索引库不存在，创建索引");
            indexOperations.create();

            //构建映射类型（通俗来说就算表结构创建）
            Document document = indexOperations.createMapping();
            indexOperations.putMapping(document);
            return true;
        }
        log.debug("[create index] 课程索引库已存在");
        return false;
    }

    /**
     * 保存课程信息到索引库
     *
     * @param searchCourseEntity 课程信息
     * @return
     */
    @Override
    public boolean saveCourse(SearchCourseEntity searchCourseEntity) {
        return restTemplate.save(searchCourseEntity) != null;
    }
}
