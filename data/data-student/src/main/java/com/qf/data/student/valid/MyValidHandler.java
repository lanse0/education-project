package com.qf.data.student.valid;

import com.qf.data.student.entity.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.Calendar;
import java.util.Date;

/**
 * 自定义校验
 * author 14526
 * create_time 2022/9/24
 */
public class MyValidHandler implements ConstraintValidator<MyValid, User> {

    /**
     * 校验用户年龄和生日是否匹配 - 自定义校验
     * @param user
     * @param context
     * @return
     */
    @Override
    public boolean isValid(User user, ConstraintValidatorContext context) {

        if (user.getAge() == null || user.getBirthday()==null){
            return true;
        }

        Integer age = user.getAge();
        Date birthday = user.getBirthday();

        //当前时间的日历对象
        Calendar now = Calendar.getInstance();
        //参数生日的日历对象
        Calendar bir = Calendar.getInstance();
        bir.setTime(birthday);

        int nowYear = now.get(Calendar.YEAR);
        int birYear = bir.get(Calendar.YEAR);

        return nowYear - birYear == age;
    }
}
