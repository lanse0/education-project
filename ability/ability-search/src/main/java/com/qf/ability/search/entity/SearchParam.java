package com.qf.ability.search.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * author 14526
 * create_time 2023/3/16
 */
@Data
@Accessors(chain = true)
public class SearchParam {

    //搜索关键字
    private String keyword;
    //规格字符串
    private String guiges;

}
