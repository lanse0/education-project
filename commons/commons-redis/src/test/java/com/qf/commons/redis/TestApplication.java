package com.qf.commons.redis;

import com.qf.commons.redis.lock.LockUtils;
import com.qf.commons.redis.utils.BloomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * author 14526
 * create_time 2022/10/27
 */
@SpringBootTest(classes = TestApplication.class)
@RunWith(SpringRunner.class)
@ComponentScan("com.qf")
@SpringBootApplication
public class TestApplication {

    @Test
    public void bloomTest() {
        //初始化
        BloomUtils.createBloom("testBloom", 0.01, 10000);
    }

    /**
     * 测试添加元素
     */
    @Test
    public void bloomTest1() {
//        boolean result1 = BloomUtils.addBloom("testBloom", "a");
//        System.out.println(result1);
//
//        boolean result2 = BloomUtils.addBloom("testBloom", "a");
//        System.out.println(result2);
        for (int i = 1; i < 10000; i++) {
            BloomUtils.addBloom("testBloom", i + "");
        }
        System.out.println("添加完成");
    }

    /**
     * 测试判断元素是否存在
     */
    @Test
    public void bloomTest2() {
//        boolean b = BloomUtils.existsBloom("testBloom", "a");
//        System.out.println(b);
//
//        boolean b1 = BloomUtils.existsBloom("testBloom", "nb");
//        System.out.println(b1);
        for (int i = 10001, j = 0; i <= 20000; i++) {
            boolean testBloom = BloomUtils.existsBloom("testBloom", i + "");
            if (testBloom) {
                System.out.println("[测试误报率] 可能存在" + i + "误报总数:" + ++j);
            }
        }
    }

    @Autowired
    Redisson redisson; //watch dog 自带机制

    /**
     * 使用分布式锁 redisson
     */
    @Test
    public void lock() {

        new Thread(()->{
            RLock mylock = redisson.getLock("mylock");
            //尝试获取锁
            while(!mylock.tryLock());
            System.out.println("线程1获得锁");

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //释放锁
            System.out.println("线程1释放了锁");
            mylock.unlock();
        }).start();

        new Thread(()->{
            RLock mylock = redisson.getLock("mylock");
            //尝试获取锁
            while(!mylock.tryLock());
            System.out.println("线程2获得锁");

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //释放锁
            System.out.println("线程2释放了锁");
            mylock.unlock();
        }).start();

        while (true){}
    }

    /**
     * 测试工具类
     */
    @Test
    public void testLockUtil(){
        new Thread(()->{
            LockUtils.lock("test");
            System.out.println("线程1获得线程锁成功");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程1解锁");
            LockUtils.unlock();
        }).start();
        new Thread(()->{
            LockUtils.lock("test");
            System.out.println("线程2获得线程锁成功");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程2解锁");
            LockUtils.unlock();
        }).start();
        while (true){}
    }
}
