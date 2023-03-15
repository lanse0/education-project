package com.qf.ability.search.service;

import com.qf.ability.search.entity.SearchCourseEntity;

/**
 * author 14526
 * create_time 2023/3/15
 */
public interface ICourseIndexSearchService {

    /**
     * 创建索引库
     * @return
     */
    boolean createIndex();

    /**
     * 保存课程信息到索引库
     * @param searchCourseEntity 课程信息
     * @return
     */
    boolean saveCourse(SearchCourseEntity searchCourseEntity);
}
