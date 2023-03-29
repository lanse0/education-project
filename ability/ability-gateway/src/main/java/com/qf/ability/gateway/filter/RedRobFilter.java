package com.qf.ability.gateway.filter;

import com.ken.event.action.apply.producer.EventTemplate;
import com.qf.ability.gateway.utils.ResponseUtils;
import com.qf.commons.data.result.R;
import com.qf.commons.data.result.RCodes;
import io.netty.buffer.ByteBufAllocator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * redis抢红包的拦截器
 * author 14526
 * create_time 2023/3/28
 */
@Component
@Slf4j
public class RedRobFilter extends AbstractGatewayFilterFactory implements GatewayFilter, Ordered {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 抢红包的lua脚本
     */
    private String robRedLua = "--抢红包的lua脚本\n" +
            "--接收红包id\n" +
            "local redid = KEYS[1]\n" +
            "--用户id\n" +
            "local uid = ARGV[1]\n" +
            "--红包的key\n" +
            "local key = 'red_envlopes_' .. redid\n" +
            "\n" +
            "--判断红包是否存在\n" +
            "local flag = redis.call('exists', key)\n" +
            "if flag == 0 then\n" +
            "  --红包不存在 红包过期\n" +
            "  return -1\n" +
            "end\n" +
            "\n" +
            "--获取红包的部分参数\n" +
            "local type = tonumber(redis.call('hget', key, 'type'))\n" +
            "local score = tonumber(redis.call('hget', key, 'score'))\n" +
            "local count = tonumber(redis.call('hget', key, 'count'))\n" +
            "\n" +
            "--判断红包是否还能抢 ~= 不等于\n" +
            "if count == 0 then\n" +
            "  --红包已抢完\n" +
            "  return -2\n" +
            "end\n" +
            "\n" +
            "--判断是否存在用户的id\n" +
            "local flag2 = redis.call('hexists', key, 'user_' .. uid)\n" +
            "if flag2 == 1 then\n" +
            "  --该用户已经抢过红包\n" +
            "  return -3\n" +
            "end\n" +
            "\n" +
            "--抢到的积分\n" +
            "local robScore = 0\n" +
            "\n" +
            "--进行抢红包计算\n" +
            "if type == 0 then\n" +
            "  --固定红包\n" +
            "  robScore = score / count\n" +
            "else\n" +
            "  --随机红包\n" +
            "  if count == 1 then\n" +
            "    --最后一个人\n" +
            "    robScore = score\n" +
            "  else\n" +
            "    --不是最后一个人 二倍均值法计算抢到的积分  1 ~ 总额/人数 * 2  lua随机数 [0-1] 含头含尾\n" +
            "    --设置随机种子\n" +
            "    local times = redis.call('time') --返回过去的秒数 和 今天过去的毫秒数?\n" +
            "    math.randomseed(times[1] + times[2])\n" +
            "    --采用二倍均值法计算抢红包的积分数\n" +
            "    robScore = math.random(score / count * 2 - 1)\n" +
            "  end\n" +
            "end\n" +
            "\n" +
            "--更新本地的redis积分 hmset 把三个命令 合成一个命令\n" +
            "redis.call('hmset', key, 'score', score - robScore, 'count', count - 1, 'user_'..uid, robScore)\n" +
            "return robScore";

    @Override
    public GatewayFilter apply(Object config) {
        return this;
        //这个类直接实现GatewayFilter接口 再实现filter方法 返回this 和这里的new内部类效果一致
//        return new GatewayFilter() {
//            @Override
//            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//                return null;
//            }
//        };
        //lambda表达式写法
//        return (exchange, chain) -> {
//          return null;
//        };
    }

    @Override
    public String name() {
        return "RedRobFilter";
    }

    /**
     * get请求获取参数
     *

     @Override public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
     //获取当前用户id 这个过滤器需要在全局认证过滤器后面 才能获取user信息 在请求头里面
     String uid = exchange.getRequest().getHeaders().getFirst("uid");
     log.debug("[rob red filter] 请求头参数 - {}",uid);
     //获取红包id get 请求的获取方式
     String redid = exchange.getRequest().getQueryParams().getFirst("redid");
     log.debug("[rob red filter] 请求体参数 - {}",redid);
     log.debug("[rob red filter] 抢红包过滤器，获取到用户和红包id: - {} - {}", uid, redid);
     //通过lua脚本执行
     Long result = stringRedisTemplate.execute(new DefaultRedisScript<Long>(robRedLua, Long.class),
     Collections.singletonList(redid),
     uid + "");
     if (result == -1) {
     //红包已过期 、 不存在
     return ResponseUtils.response(exchange, R.createFail(RCodes.RED_TIMEOUT, result));
     } else if (result == -2) {
     return ResponseUtils.response(exchange, R.createFail(RCodes.RED_OVER, result));
     } else if (result == -3) {
     return ResponseUtils.response(exchange, R.createFail(RCodes.RED_EXISTS, result));
     }
     //红包已经抢到
     Map<String, Integer> map = new HashMap<>();
     map.put("redid", Integer.parseInt(redid));
     map.put("uid", Integer.parseInt(uid));
     map.put("robScore", result.intValue()); //long转integer
     //事件总线 发布抢红包事件 让红包服务监听 进行数据库写入
     EventTemplate.sendReliable("rob-red", map);
     //抢红包业务已经执行完 返回结果
     return ResponseUtils.response(exchange, R.create(result));
     }
     */

    /**
     * post获取参数的方式
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String uid = exchange.getRequest().getHeaders().getFirst("uid");
//        Integer uid = (int)(Math.random() * 100000);
        //获取经过RequestGlobalFilter处理的请求体参数
        String redidStr = exchange.getAttribute("POST_BODY");
        log.debug("[rob red filter] 请求体参数 - {}", redidStr);
        String[] split = redidStr.split("=");
        String redid = split[1];
        log.debug("[rob red filter] 抢红包过滤器，获取到用户和红包id: - {} - {}", uid, redid);
        Long result = stringRedisTemplate.execute(new DefaultRedisScript<Long>(robRedLua, Long.class),
                Collections.singletonList(redid),
                uid);
        if (result == -1) {
            //红包已过期 、 不存在
            return ResponseUtils.response(exchange, R.createFail(RCodes.RED_TIMEOUT, result));
        } else if (result == -2) {
            //红包已抢完
            return ResponseUtils.response(exchange, R.createFail(RCodes.RED_OVER, result));
        } else if (result == -3) {
            //红包已经抢过
            return ResponseUtils.response(exchange, R.createFail(RCodes.RED_EXISTS, result));
        }
        //红包已经抢到
        Map<String, Integer> map = new HashMap<>();
        map.put("redid", Integer.parseInt(redid));
        map.put("uid", Integer.parseInt(uid));
        map.put("robScore", result.intValue()); //long转integer
        //事件总线 发布抢红包事件 让红包服务监听 进行数据库写入
        EventTemplate.sendReliable("rob-red", map);
        //抢红包业务已经执行完 返回结果
        return ResponseUtils.response(exchange, R.create(result));
    }


    @Override
    public int getOrder() {
        //此拦截器需要拿到header中的uid  让authGlobalFilter执行完修改了header，设置了uid之后再执行此拦截器
        return 2;
    }
}
