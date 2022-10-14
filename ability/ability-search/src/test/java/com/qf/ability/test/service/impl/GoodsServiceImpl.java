package com.qf.ability.test.service.impl;

import com.qf.ability.test.entity.Goods;
import com.qf.ability.test.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Service;

/**
 * author 14526
 * create_time 2022/10/14
 */
@Service
public class GoodsServiceImpl implements IGoodsService {

    //注入ES的模板对象
    @Autowired
    private ElasticsearchRestTemplate restTemplate;

    /**
     * 创建索引库
     *
     * @return
     */
    @Override
    public boolean createIndex() {
        IndexOperations indexOperations = restTemplate.indexOps(Goods.class);
        return indexOperations.create();
    }

    /**
     * 创建映射类型
     *
     * @return
     */
    @Override
    public boolean createMapping() {
        IndexOperations indexOperations = restTemplate.indexOps(Goods.class);
        Document document = indexOperations.createMapping();
        return indexOperations.putMapping(document);
    }

    /**
     * 添加(修改)文档
     *
     * @param goods
     * @return
     */
    @Override
    public boolean addGoods(Goods goods) {
        Goods result = restTemplate.save(goods);
        return result != null;
    }

    /**
     * 删除id商品
     * @param id
     * @return
     */
    @Override
    public boolean deleteGoods(Integer id) {
        String delete = restTemplate.delete(id+"", IndexCoordinates.of("goods_index"));
        System.out.println("删除状态："+delete);
        return true;
    }
}
