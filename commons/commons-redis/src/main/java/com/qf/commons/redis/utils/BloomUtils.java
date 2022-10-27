package com.qf.commons.redis.utils;

import com.qf.commons.core.utils.ApplicationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Collections;

/**
 * bloom过滤器的工具类
 * author 14526
 * create_time 2022/10/27
 */
public class BloomUtils {

    private static StringRedisTemplate redisTemplate;

    static {
        //使用工具手动注入bean
        redisTemplate = ApplicationUtils.getBean(StringRedisTemplate.class);
    }

    private static String createBloomLua = "--创建bloom过滤器的脚本\n" +
            "local key = 'bloom-'..KEYS[1]\n" +
            "--误报率\n" +
            "local x = tonumber(ARGV[1])\n" +
            "--最大数量\n" +
            "local count = tonumber(ARGV[2])\n" +
            "\n" +
            "--创建bloom过滤器\n" +
            "local ex = redis.call('exists',key)\n" +
            "--若不存在\n" +
            "if ex == 0 then\n" +
            "    --创建bloom过滤器\n" +
            "    redis.call('bf.reserve',key,x,count)\n" +
            "    return 1\n" +
            "end\n" +
            "\n" +
            "return 0";

    private static String addBloomLua = "--添加bloom过滤器元素的脚本\n" +
            "local key = 'bloom-'..KEYS[1]\n" +
            "local value = ARGV[1]\n" +
            "\n" +
            "--返回1 表示已经添加成功 0表示可能存在\n" +
            "return redis.call('bf.add',key,value)";

    private static String existsBloomLua = "--判断元素是否存在\n" +
            "local key = 'bloom-'..KEYS[1]\n" +
            "local value = ARGV[1]\n" +
            "\n" +
            "--返回1 表示可能存在 0表示一定不存在\n" +
            "return redis.call('bf.exists',key,value)";

    /**
     * 创建一个bloom过滤器
     *
     * @param key   过滤器名称
     * @param x     误报率 0.01
     * @param count 元素的大概数量
     * @return
     */
    public static boolean createBloom(String key, double x, int count) {
        Long result = redisTemplate.execute(new DefaultRedisScript<>(createBloomLua, Long.class),
                Collections.singletonList(key),
                x + "", count + "");
        return result == 1;
    }

    /**
     * 添加元素到bloom过滤器
     *
     * @param key
     * @param value
     * @return
     */
    public static boolean addBloom(String key, String value) {
        Long result = redisTemplate.execute(new DefaultRedisScript<>(addBloomLua, Long.class),
                Collections.singletonList(key),
                value + "");
        return result == 1;
    }

    /**
     * 判断元素是否存在
     * @param key
     * @param value
     * @return
     */
    public static boolean existsBloom(String key,String value){
        Long result = redisTemplate.execute(new DefaultRedisScript<>(existsBloomLua, Long.class),
                Collections.singletonList(key),
                value + "");
        return result == 1;
    }
}
