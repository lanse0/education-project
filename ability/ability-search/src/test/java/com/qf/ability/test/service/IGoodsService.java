package com.qf.ability.test.service;

import com.qf.ability.test.entity.Goods;

/**
 * 商品操作业务层
 * author 14526
 * create_time 2022/10/14
 */
public interface IGoodsService {
    boolean createIndex();
    boolean createMapping();
    boolean addGoods(Goods goods);

    boolean deleteGoods(Integer id);
}
