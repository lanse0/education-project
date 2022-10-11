package com.qf.commons.web.utils;

import com.qf.commons.data.base.BaseUser;

/**
 * 获取登录用户的工具类
 * author 14526
 * create_time 2022/10/11
 */
public class UserUtils {
    private static ThreadLocal<BaseUser> userThreadLocal = new ThreadLocal<>();

    public static void setUserThreadLocal(BaseUser baseUser){
        UserUtils.userThreadLocal.set(baseUser);
    }

    public static BaseUser getUser(){
        return userThreadLocal.get();
    }

    public static void clear(){
        userThreadLocal.remove();
    }
}
