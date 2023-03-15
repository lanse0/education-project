package com.qf.ability.search.listener;

import com.ken.event.action.apply.consumer.IKenEventHandler;
import com.ken.event.action.apply.consumer.KenEvent;
import com.ken.event.standard.entity.KenMessage;
import com.qf.ability.search.entity.SearchCourseEntity;
import com.qf.ability.search.entity.SearchGuigeEntity;
import com.qf.ability.search.service.ICourseIndexSearchService;
import com.qf.commons.core.utils.QfBeanUtils;
import com.qf.data.course.dto.CourseSearchDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 课程新增的事件处理器
 * author 14526
 * create_time 2023/3/15
 */
@KenEvent("course_insert")
@Slf4j
public class CourseInsertEventHandler implements IKenEventHandler<CourseSearchDto> {

    @Autowired
    private ICourseIndexSearchService service;

    @Override
    public void eventHandler(CourseSearchDto courseSearchDto, KenMessage kenMessage) {
        log.debug("[event handler] 接收到课程新增的消息 - {}", courseSearchDto);
        //转换实体类courseInfoInput => SearchCourseEntity 反射
        SearchCourseEntity searchCourseEntity = QfBeanUtils.copyBean(courseSearchDto, SearchCourseEntity.class);

        //需要转换实体类里面的规格列表 用工具直接转换无法转换内部的guiges集合实体类型（BeanUtils.copy方法不能拷贝集合），需要手动转换设置
//        List<SearchGuigeEntity> searchGuigeEntities = courseSearchDto.getGuiges().stream().map(
//                guige -> new SearchGuigeEntity().setGid(guige.getGid()).setValid(guige.getValid())
//        ).collect(Collectors.toList());
        //用封装的工具类转换集合类型
        List<SearchGuigeEntity> searchGuigeEntities = QfBeanUtils.copyList(courseSearchDto.getGuiges(), SearchGuigeEntity.class);

        searchCourseEntity.setGuiges(searchGuigeEntities);
        log.debug("[event handler] 课程实体转换 - {}", searchCourseEntity);

        //调用业务层 保存课程信息到es
        service.saveCourse(searchCourseEntity);
    }
}
