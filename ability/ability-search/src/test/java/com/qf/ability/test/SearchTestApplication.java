package com.qf.ability.test;

import com.qf.ability.search.application.SearchApplication;
import com.qf.ability.test.entity.Goods;
import com.qf.ability.test.service.IGoodsService;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.Query;

import java.util.Date;
import java.util.List;

/**
 * author 14526
 * create_time 2022/10/14
 */
@SpringBootTest(classes = SearchApplication.class)
public class SearchTestApplication {

    @Autowired
    private IGoodsService goodsService;


    @Test
    public void test() {
        System.out.println("创建索引库");
        boolean flag = goodsService.createIndex();
        System.out.println("创建状态：" + flag);

        boolean mapping = goodsService.createMapping();
        System.out.println("创建映射类型状态："+mapping);

        Goods goods = new Goods()
                .setId(1)
                .setTitle("美的滚筒洗衣机")
                .setPrice(999.99)
                .setImage("http://1.jpg")
                .setCreateTime(new Date())
                .setInfo("性价比很高的洗衣机，牛逼");
        System.out.println("添加文档状态：" + goodsService.addGoods(goods));
        Goods goods2 = new Goods()
                .setId(2)
                .setTitle("美的波轮电视机")
                .setPrice(2999.99)
                .setImage("http://2.jpg")
                .setCreateTime(new Date())
                .setInfo("电视机中的直升机");
        System.out.println("添加文档状态：" + goodsService.addGoods(goods2));

//        goodsService.deleteGoods(1);
    }

    @Autowired
    private ElasticsearchRestTemplate template;

    /**
     * 通过Java API 调用ES实现搜索
     */
    @Test
    public void testSearch() {
        //term搜索
        QueryBuilder termQuery = QueryBuilders.termQuery("title", "美的洗衣机");
        //match搜索
        QueryBuilder matchQuery = QueryBuilders.matchQuery("title", "美的洗衣机");
        //multi_match查询
        //MultiMatchQueryBuilder multiMatchQuery = QueryBuilders.multiMatchQuery("美的洗衣机", "title", "info");
        MultiMatchQueryBuilder multiMatchQuery = QueryBuilders.multiMatchQuery("美的洗衣机")
                .field("title",20)
                .field("info",1);
        //range查询
        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("price").gte(1000).lte(3000);

        //创建一个查询对象
        Query query = new NativeSearchQuery(rangeQuery);
        //term搜索 通过实体类判断去那个索引库搜索
        SearchHits<Goods> hits = template.search(query, Goods.class);
        //获得搜索的结果列表
        List<SearchHit<Goods>> searchHits = hits.getSearchHits();
        System.out.println(hits);
        System.out.println("搜索的结果数量："+hits.getTotalHits());
        for (SearchHit<Goods> searchHit : searchHits) {
            //SearchHit -> 表示一个查询结果记录 （Document）
            System.out.println(searchHit.getContent());
        }
    }
}