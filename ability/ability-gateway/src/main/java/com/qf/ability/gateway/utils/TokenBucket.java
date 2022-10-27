package com.qf.ability.gateway.utils;

import com.ken.event.commons.utils.ApplicationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 令牌桶的工具类 - Redis key Hash结构
 * author 14526
 * create_time 2022/10/27
 */
@Slf4j
public class TokenBucket {

    /**
     * 令牌桶的key - 通过不同的key 实现不同的限流
     */
    private String key;

    /**
     * 令牌桶的剩余令牌数
     */
    private Integer tokens;
    /**
     * 最大令牌数
     */
    private Integer maxTokens;
    /**
     * 每秒生成多少令牌
     */
    private Integer tokensSec;

    //redis的操作方式 这里不使用自动注入 令牌桶需要有多个实例 不是单例模式 需要多个实例 使用手动注入对象
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 获取令牌的lua脚本
     */
    private String getTokenLua = "--获取对应的令牌桶的key\n" +
            "local key = 'token-bucket-'..KEYS[1]\n" +
            "--获取当前需要从令牌桶中拿到几个令牌\n" +
            "--tonumber将字符串转换成数字\n" +
            "local getTokens = tonumber(ARGV[1])\n" +
            "\n" +
            "--当前令牌桶中的相关参数\n" +
            "--获取当前令牌桶中的剩余令牌数\n" +
            "local tokens = tonumber(redis.call('hget',key,'tokens'))\n" +
            "--获取最大令牌数\n" +
            "local maxTokens = tonumber(redis.call('hget',key,'maxTokens'))\n" +
            "--获取每秒生成的令牌数\n" +
            "local tokensSec = tonumber(redis.call('hget',key,'tokensSec'))\n" +
            "--获取令牌的生成时间(微妙)\n" +
            "local tokensTime = tonumber(redis.call('hget',key,'tokensTime'))\n" +
            "\n" +
            "--获取当前的系统时间\n" +
            "local nowTimes = redis.call('time') --第一个值是当前时间 第二个值是这一秒已经逝去的微妙数\n" +
            "local nowMics = tonumber(nowTimes[1] * 1000000 + tonumber(nowTimes[2]))\n" +
            "\n" +
            "--基于系统时间重新计算令牌数量\n" +
            "if nowMics>tokensTime then\n" +
            "    --只有当前时间 大于上次计算令牌的时间要大 才会有新令牌产生\n" +
            "    --计算生成一个令牌需要的时长\n" +
            "    local oneTokensTime = 1000000/tokensSec\n" +
            "    --当前时间差生成了多少令牌\n" +
            "    local createTokens = (nowMics-tokensTime)/oneTokensTime\n" +
            "    --计算剩余令牌数 math.min 返回最小值 令牌数不能超过最大令牌\n" +
            "    tokens = math.min(tokens+createTokens,maxTokens)\n" +
            "    --更新令牌生成的时间\n" +
            "    tokensTime = nowMics\n" +
            "end\n" +
            "\n" +
            "--获取令牌\n" +
            "if tokens<getTokens then\n" +
            "    --更新令牌数\n" +
            "    redis.call('hmset',key,'tokens',tokens,'tokensTime',tokensTime)\n" +
            "    --令牌不足 返回-1\n" +
            "    return -1\n" +
            "end\n" +
            "\n" +
            "--令牌充足 更新redis中的令牌 返回成功\n" +
            "tokens = tokens - getTokens\n" +
            "redis.call('hmset',key,'tokens',tokens,'tokensTime',tokensTime)\n" +
            "return 1";


    /**
     * 初始化令牌桶
     *
     * @param key
     * @param maxTokens
     * @param tokensSec
     */
    public TokenBucket(String key, Integer maxTokens, Integer tokensSec) {
        this.key = key;
        this.tokens = maxTokens;
        this.maxTokens = maxTokens;
        this.tokensSec = tokensSec;
        //使用事件总线的工具手动注入对象
        this.stringRedisTemplate = ApplicationUtils.getBean(StringRedisTemplate.class);
        initRedis();
    }

    /**
     * 初始化Redis中的hash结构
     */
    public void initRedis() {

        Map<String, String> params = new HashMap<>();
        params.put("tokens", this.tokens + ""); //令牌桶的剩余令牌数
        params.put("maxTokens", this.maxTokens + ""); //令牌桶的最大令牌书
        params.put("tokensSec", this.tokensSec + ""); //每秒生产的令牌数
        //令牌数生成的对应时间 微妙值
        params.put("tokensTime", TimeUnit.MILLISECONDS.toMicros(System.currentTimeMillis()) + "");

        //设置redis的令牌桶
        stringRedisTemplate.opsForHash().putAll("token-bucket-" + this.key, params);

        log.debug("[token bucket] 令牌桶初始化完成！{} - {}", this.key, params);
    }

    /**
     * 获取令牌
     *
     * @param getTokens 需要获取多少令牌
     * @return 返回true - 表示已经获取到对应令牌，放行 否则拒绝
     */
    public boolean getTokens(Integer getTokens) {
        //通过lua脚本 实现令牌的获取 - 因为redis单线程的原因 可以保证获取令牌时，是线程安全的
        Long result = stringRedisTemplate.execute(new DefaultRedisScript<>(getTokenLua, Long.class),
                Collections.singletonList(this.key), //keys 脚本的key 参数是一个集合
                getTokens + ""); //argv 脚本的argv
        return result == 1;
    }
}
