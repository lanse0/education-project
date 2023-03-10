package com.qf.business.course.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.business.course.dao.CourseInfoDao;
import com.qf.business.course.service.CourseInfoGuigeService;
import com.qf.commons.core.utils.QfBeanUtils;
import com.qf.commons.web.aspect.annotation.GetUser;
import com.qf.commons.web.utils.UserUtils;
import com.qf.data.course.entity.CourseInfo;
import com.qf.business.course.service.CourseInfoService;
import com.qf.data.course.entity.CourseInfoGuige;
import com.qf.data.course.vo.input.CourseInfoInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 课程信息表(CourseInfo)表服务实现类
 *
 * @author makejava
 * @since 2023-03-10 13:52:41
 */
@Service("courseInfoService")
@Slf4j
public class CourseInfoServiceImpl extends ServiceImpl<CourseInfoDao, CourseInfo> implements CourseInfoService {

    @Autowired
    private CourseInfoGuigeService courseInfoGuigeService;

    /**
     * 根据教师ID查询课程
     *
     * @param tid
     * @return
     */
    @Override
    public List<CourseInfo> queryByTid(Integer tid) {
        List<CourseInfo> list = this.query().eq("teacher_id", tid).list();

        return list;
    }

    /**
     * 保存课程信息
     *
     * @param courseInfoInput
     * @return
     */
    @Override
    @GetUser
    @Transactional
    public int save(CourseInfoInput courseInfoInput) {

        //保存基本课程
        CourseInfo courseInfo = QfBeanUtils.copyBean(courseInfoInput, CourseInfo.class);
        //设置课程申请人教师id
        courseInfo.setTeacherId(UserUtils.getUser().getId());
        log.debug("[save course] 保存的课程信息 - {}",courseInfo);
        //保存课程信息
        this.save(courseInfo);

        //-----------------处理课程和规格的关联关系-----------------
        //guiges=[{"gid":1,"selected":1},{"gid":2,"selected":5},{"gid":4,"selected":13}]
        List<CourseInfoGuige> courseInfoGuiges = new ArrayList<>();
        JSONArray jsonArray = JSON.parseArray(courseInfoInput.getGuiges());
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            Integer gid = obj.getInteger("gid"); //规格ID
            Integer selected = obj.getInteger("selected"); //规格选中的的值的id

            CourseInfoGuige courseInfoGuige = new CourseInfoGuige()
                    .setCid(courseInfo.getId()) //拿到刚刚保存课程的主键
                    .setCgid(gid)
                    .setCgvid(selected);
            courseInfoGuiges.add(courseInfoGuige);
        }
        //保存课程规格关联关系
        courseInfoGuigeService.saveBatch(courseInfoGuiges);

        return 1;
    }
}

