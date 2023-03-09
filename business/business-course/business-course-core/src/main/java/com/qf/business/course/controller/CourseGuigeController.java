package com.qf.business.course.controller;

import com.qf.business.course.service.CourseGuigeService;
import com.qf.business.course.service.CourseGuigeValService;
import com.qf.commons.data.result.R;
import com.qf.data.course.entity.CourseGuige;
import com.qf.data.course.entity.CourseGuigeVal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author 14526
 * create_time 2023/3/7
 */
@RestController
@RequestMapping("/course/guige")
public class CourseGuigeController {

    @Autowired
    private CourseGuigeService courseGuigeService;
    @Autowired
    private CourseGuigeValService courseGuigeValService;

    /**
     * 规格列表
     *
     * @return
     */
    @RequestMapping("/list")
    public R list() {
        List<CourseGuige> list = courseGuigeService.list();
        return R.create(list);
    }

    /**
     * 新增规格
     * @param guige
     * @return
     */
    @RequestMapping("/insert")
    public R insert(CourseGuige guige){
        courseGuigeService.save(guige);
        return R.create("succ");
    }


    /**
     * 根据规格的id查询当前规格的所有值
     *
     * @param gid
     * @return
     */
    @RequestMapping("/vals")
    public R guigeVals(Integer gid) {
        List<CourseGuigeVal> list = courseGuigeValService
                .query()
                .eq("gid", gid)
                .list();    //业务逻辑最好写在service层
        return R.create(list);
    }

    /**
     * 新增规格的可选值
     *
     * @param guigeVal
     * @return
     */
    @RequestMapping("/insertVal")
    public R guigeValInsert(CourseGuigeVal guigeVal) {
        courseGuigeValService.save(guigeVal);
        return R.create(guigeVal);
    }

    /**
     * 删除规格值
     * @param id
     * @return
     */
    @RequestMapping("/delVal")
    public R guigeValDel(Integer id){
        courseGuigeValService.removeById(id);
        return R.create("succ");
    }
}
