package com.qf.ability.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.qf.ability.search.entity.SearchCourseEntity;
import com.qf.ability.search.entity.SearchGuigeEntity;
import com.qf.ability.search.entity.SearchParam;
import com.qf.ability.search.service.ICourseIndexSearchService;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.query.HighlightQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

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
        if (!indexOperations.exists()) {
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

    /**
     * 进行业务搜索
     *
     * @param searchParam
     * @return
     */
    @Override
    public List<SearchCourseEntity> searchByParam(SearchParam searchParam) {

        //主查询 把下面查询关键字的查询 和查询规格的查询 组装起来
        BoolQueryBuilder mainQuery = QueryBuilders.boolQuery();

        //构建查询对象 多匹配查询生成器 根据关键字查询
        MultiMatchQueryBuilder multiMatchQuery = QueryBuilders.multiMatchQuery(searchParam.getKeyword())
                .field("subject")
                .field("subject.pinyin", 0.2f)
                .field("info");
        mainQuery.must(multiMatchQuery);

        //处理规格的条件 根据规格查询
        if (!StringUtils.isEmpty(searchParam.getGuiges())) {
            List<SearchGuigeEntity> guigesList = JSON.parseArray(searchParam.getGuiges(), SearchGuigeEntity.class);
            log.debug("[course search] json解析得到规格对象 - {}",guigesList);
            //循环构建查询条件
            for (SearchGuigeEntity searchGuigeEntity : guigesList) {
                //嵌套查询
                NestedQueryBuilder guigeQuery = QueryBuilders.nestedQuery("guiges",
                        QueryBuilders.boolQuery()
                                .must(QueryBuilders.termQuery("guiges.gid", searchGuigeEntity.getGid()))
                                .must(QueryBuilders.termQuery("guiges.valid", searchGuigeEntity.getValid()))
                        , ScoreMode.Avg);
                //规格查询也会作为must放入主查询中
                mainQuery.must(guigeQuery);
            }
        }

        //本机搜索查询
        NativeSearchQuery query = new NativeSearchQuery(mainQuery);
        //设置高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("subject");
        highlightBuilder.preTags("<a style='color:red'>");
        highlightBuilder.postTags("</a>");
        HighlightQuery highlightQuery = new HighlightQuery(highlightBuilder);
        query.setHighlightQuery(highlightQuery);
        //进行查询 搜索命中
        SearchHits<SearchCourseEntity> searchHits = restTemplate.search(query, SearchCourseEntity.class);
        //处理搜索结果
        List<SearchCourseEntity> searchCourseEntities = new ArrayList<>();
        for (SearchHit<SearchCourseEntity> searchHit : searchHits) {
            //获得搜索的结果对象
            SearchCourseEntity entity = searchHit.getContent();
            //处理高亮信息
            List<String> highlightField = searchHit.getHighlightField("subject");
            if (!CollectionUtils.isEmpty(highlightField)) {
                entity.setSubject(highlightField.get(0));
            }
            //放入list集合中
            searchCourseEntities.add(entity);
        }

        return searchCourseEntities;
    }


}
