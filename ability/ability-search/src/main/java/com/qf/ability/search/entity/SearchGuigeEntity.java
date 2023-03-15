package com.qf.ability.search.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 搜索规格的实体
 * author 14526
 * create_time 2023/3/15
 */
@Data
@Accessors(chain = true)
public class SearchGuigeEntity {

    //规格id
    @Field(type = FieldType.Integer)
    private Integer gid;
    //规格选中的值的id
    @Field(type = FieldType.Integer)
    private Integer valid;
}
