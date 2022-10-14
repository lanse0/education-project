package com.qf.ability.test.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * author 14526
 * create_time 2022/10/14
 */
@Data
@Accessors(chain = true)
@Document(indexName = "goods_index", shards = 1, replicas = 0) //索引名称 分片数量 副本数量
public class Goods {
    @Id
    private Integer id;
    @Field(type = FieldType.Text, index = true, store = false, analyzer = "ik_max_word")
    private String title;
    @Field(type = FieldType.Double)
    private Double price;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String info;
    @Field(type = FieldType.Keyword, index = false)
    private String image;
    //时间存储到文档时 无法设置时区 和实际时间有偏差 解决方法：时间类型使用long
//    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    @Field(type = FieldType.Long)
    private Date createTime;
}
