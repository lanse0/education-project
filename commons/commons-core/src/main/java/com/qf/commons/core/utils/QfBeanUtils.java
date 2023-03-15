package com.qf.commons.core.utils;

import com.qf.commons.core.exception.ServiceException;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    public static <T> List<T> copyList(Collection collection, Class<T> tcls) {
        if (collection == null || collection.size() == 0) return null;
        List<T> targetList = new ArrayList<>();
        //循环集合
        for (Object sourceObj : collection) {
            //逐个拷贝
            T targetObj = copyBean(sourceObj, tcls);
            targetList.add(targetObj);
        }
        return targetList;
    }
}
