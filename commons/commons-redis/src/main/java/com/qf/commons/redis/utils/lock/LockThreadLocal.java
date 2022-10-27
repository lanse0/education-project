package com.qf.commons.redis.utils.lock;

import org.redisson.api.RLock;

/**
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
