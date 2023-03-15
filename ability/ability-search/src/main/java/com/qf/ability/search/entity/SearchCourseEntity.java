package com.qf.ability.search.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 课程搜索的实体对象
 * author 14526
 * create_time 2023/3/15
 */
@Data
@Accessors(chain = true)
@Document(indexName = "course_index", shards = 1, replicas = 0)//索引名 分片 副本
public class SearchCourseEntity implements Serializable {

    @Id
    private Integer id;
    //教师姓名
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String teacherName;
    //课程分类的id - 外键(course_type表id)
    @Field(type = FieldType.Integer)
    private Integer tid;
    //课程标题
    @MultiField(
            mainField = @Field(type = FieldType.Text, analyzer = "ik_max_word"),
            otherFields = @InnerField(suffix = "pinyin", type = FieldType.Text, analyzer = "pinyin")
    )
    private String subject;
    //课程封面
    @Field(type = FieldType.Keyword, index = false)
    private String fengmian;
    //课程简介
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String info;
    //课程金额 -1 为免费
    @Field(type = FieldType.Double)
    private Double price;
    //课程开始i时间
    @Field(type = FieldType.Long)
    private Date beginTime;
    //课程结束时间
    @Field(type = FieldType.Long)
    private Date endTime;

    //课程相关的规格列表
    @Field(type = FieldType.Nested) //nested嵌套
    private List<SearchGuigeEntity> guiges = new ArrayList<>(); //这里需要new出来 如果不new 会构建成一个普通对象object 进行添加元素操作时 就会报错
}
