package com.qf.commons.redis.lock;

import com.qf.commons.core.utils.ApplicationUtils;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

/**
 * redisson分布式锁工具类
 * author 14526
 * create_time 2022/10/27
 */
@Slf4j
public class LockUtils {

    private static Redisson redisson;

    static {
        redisson = ApplicationUtils.getBean(Redisson.class);
    }

    /**
     * 阻塞加锁
     */
    public static void lock(String key) {
        RLock lock = redisson.getLock("lock-"+key);
        while (!lock.tryLock()) ;
        log.debug("[cluster lock] 成功获得分布式锁!");
        //加锁成功把锁放到线程
        LockThreadLocal.putRLocal(lock);
    }

    /**
     * 加锁的过期时间 在规定时间内没有拿到锁就直接返回false
     * @param key
     * @param timeout
     * @param unit
     */
    public static boolean lock(String key, long timeout, TimeUnit unit){
        RLock lock = redisson.getLock(key);
        try {
            boolean flag = lock.tryLock(timeout, unit);
            if (flag){
                log.debug("[cluster lock] 成功获得分布式锁！");
                LockThreadLocal.putRLocal(lock);
                return true;
            }
        } catch (InterruptedException e) {
        }
        return false;
    }

    /**
     * 解锁分布式锁
     */
    public static void unlock(){
        RLock rLock = LockThreadLocal.getRLock();
        if (rLock!=null && rLock.isLocked()){
            log.debug("[cluster lock] 解除分布式锁");
            rLock.unlock();
            LockThreadLocal.clear();
        }
    }
}
