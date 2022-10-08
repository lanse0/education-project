package com.qf.commons.core.utils;

import com.qf.commons.core.exception.ServiceException;
import org.springframework.beans.BeanUtils;

/**
 * 工具类 把任意一个个对象转换成为想要的对象  vo -> entity
 * author 14526
 * create_time 2022/10/8
 */
public class QfBeanUtils {

    public static <T> T copyBean(Object source, Class<T> tcls) {
        try {
            T t = tcls.newInstance();
            BeanUtils.copyProperties(source, t);
            return t;
        } catch (Exception e) {
            throw new ServiceException(500, tcls + "初始化异常");
        }
    }
}
