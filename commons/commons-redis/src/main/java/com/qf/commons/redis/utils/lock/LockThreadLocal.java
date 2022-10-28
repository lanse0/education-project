package com.qf.commons.redis.utils.lock;

import org.redisson.api.RLock;

/**
 * 用于锁的传递 工具类加锁 解锁 一种方法是上锁返回值是RLock 需要解锁再用这个对象解锁 另一种就是线程变量
 * author 14526
 * create_time 2022/10/27
 */
public class LockThreadLocal {

    private static ThreadLocal<RLock> rLockThreadLocal = new ThreadLocal<>();

    public static void putRLocal(RLock rLock){
        rLockThreadLocal.set(rLock);
    }

    public static RLock getRLock(){
        return rLockThreadLocal.get();
    }

    public static void clear(){
        rLockThreadLocal.remove();
    }
}
