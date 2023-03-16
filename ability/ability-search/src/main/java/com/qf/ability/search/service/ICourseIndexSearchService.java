package com.qf.ability.search.service;

import com.qf.ability.search.entity.SearchCourseEntity;
import com.qf.ability.search.entity.SearchParam;

import java.util.List;

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

    /**
     * 进行业务搜索
     * @param searchParam
     * @return
     */
    List<SearchCourseEntity> searchByParam(SearchParam searchParam);
}
