package com.qf.business.course.service;

import com.qf.business.course.tcc.ICourseTcc;
import com.qf.business.student.feign.StuFeign;
import com.qf.commons.data.result.R;
import com.qf.data.student.vo.input.UserRegisterInput;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author 14526
 * create_time 2022/10/5
 */
@Service
@Slf4j
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private StuFeign stuFeign;
    @Autowired
    private ICourseTcc courseTcc;

    @Override
    public String query() {
        //        System.out.println("请求课程的Controller");
        log.debug("请求课程的Controller");
//        List<User> users = (List<User>) stuFeign.list(1).getData();
        //请求学生
        R result = stuFeign.list(1);
        System.out.println("获取到学生数据---》" + result);
        return "课程信息，" + result;
    }

    @Override
    public R getUserById(Integer id) {
        R r = stuFeign.getById(id);
        System.out.println(r.getData());
        return r;
    }

    /**
     * seata分布式事务测试
     * @return
     */
    @Override
    @GlobalTransactional
    public String insertStu() {

        //TCC的业务调用
        courseTcc.query(10);

        UserRegisterInput userRegisterInput = new UserRegisterInput();
        userRegisterInput.setUsername("test");
        userRegisterInput.setPassword("134");
        userRegisterInput.setName("测试");
        userRegisterInput.setAge(0);
        userRegisterInput.setSex(0);
        userRegisterInput.setRole(0);

        R rs = stuFeign.insert(userRegisterInput);

        try {
            Thread.sleep(65000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println(1/0);
        return "插入结果:" + rs;
    }
}
