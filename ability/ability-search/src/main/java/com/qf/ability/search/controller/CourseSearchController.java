package com.qf.ability.search.controller;

import com.qf.ability.search.entity.SearchCourseEntity;
import com.qf.ability.search.entity.SearchParam;
import com.qf.ability.search.service.ICourseIndexSearchService;
import com.qf.commons.data.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author 14526
 * create_time 2023/3/16
 */
@RestController
@RequestMapping("/search/course")
@Slf4j
public class CourseSearchController {

    @Autowired
    private ICourseIndexSearchService courseIndexSearchService;

    @RequestMapping("/courseByKeyword")
    public R courseSearch(SearchParam searchParam) {
        log.debug("[course search] 接收到搜索条件 - {}",searchParam);
        List<SearchCourseEntity> result = courseIndexSearchService.searchByParam(searchParam);
        return R.create(result);
    }
}
