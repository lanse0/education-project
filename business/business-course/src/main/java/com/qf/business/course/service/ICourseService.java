package com.qf.business.course.service;

import com.qf.commons.data.result.R;
import org.springframework.stereotype.Service;

/**
 * author 14526
 * create_time 2022/10/5
 */
public interface ICourseService {
    String query();
    R getUserById(Integer id);
    String insertStu();
}
