package com.qf.business.redenvlopes.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.data.redenvlopes.dto.RedEnvelopesDto;
import com.data.redenvlopes.entity.RedEnvelopes;
import com.data.redenvlopes.entity.RedEnvelopesDetails;
import com.data.redenvlopes.vo.input.RedEnvlopesInput;
import com.ken.event.action.apply.producer.EventTemplate;
import com.qf.business.redenvlopes.core.dao.RedEnvelopesDao;
import com.qf.business.redenvlopes.core.service.RedEnvelopesDetailsService;
import com.qf.business.redenvlopes.core.service.RedEnvelopesService;
import com.qf.business.user.feign.WxUserFeign;
import com.qf.commons.core.exception.ServiceException;
import com.qf.commons.core.utils.QfBeanUtils;
import com.qf.commons.redis.annotation.KLock;
import com.qf.commons.web.aspect.annotation.GetUser;
import com.qf.commons.web.utils.UserUtils;
import com.qf.data.user.entity.WxScoreDetails;
import com.qf.data.user.vo.input.WxScoreUpdateInput;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.concurrent.TimeUnit;


/**
 * 红包表(RedEnvelopes)表服务实现类 - redis版本
 *
 * @author makejava
 * @since 2023-03-21 23:45:36
 */
@Service
@Slf4j
@Primary //自动注入这个类 全局只能有一个
public class RedEnvelopesServiceImpl2 extends ServiceImpl<RedEnvelopesDao, RedEnvelopes> implements RedEnvelopesService {

    @Autowired
    private WxUserFeign wxUserFeign;
    @Autowired
    private RedEnvelopesDetailsService redEnvelopesDetailsService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //创建红包的lua脚本
    private String createRedLua = "-- 接受红包相关参数\n" +
            "local redid = KEYS[1]\n" +
            "local type = ARGV[1]\n" +
            "local score = ARGV[2]\n" +
            "local count = ARGV[3]\n" +
            "\n" +
            "-- 生成一个key\n" +
            "local key = 'red_envlopes_'..redid\n" +
            "\n" +
            "-- 判断当前红包是否存在\n" +
            "local result = redis.call('exists',key)\n" +
            "if result == 1 then\n" +
            "-- 红包已存在\n" +
            "  return -1\n" +
            "end\n" +
            "\n" +
            "-- 创建红包\n" +
            "redis.call('hset',key,'type',type)\n" +
            "redis.call('hset',key,'score',score)\n" +
            "redis.call('hset',key,'count',count)\n" +
            "\n" +
            "return 1";

    /**
     * 红包回退的lua脚本
     */
    private String redBackLua = "-- 获取红包id\n" +
            "local redid = KEYS[1]\n" +
            "-- 获取红包key\n" +
            "local key = 'red_envlopes_' .. redid\n" +
            "-- 判断红包是否存在\n" +
            "local flag = redis.call('exists',key)\n" +
            "if flag == 0 then\n" +
            "    --红包不存在\n" +
            "    return -1\n" +
            "end\n" +
            "-- 获取红包剩余积分\n" +
            "local score = tonumber(redis.call('hget',key,'score'))\n" +
            "--删除当前红包key\n" +
            "redis.call('del',key)\n" +
            "if score == 0 then\n" +
            "    -- 红包已被抢完\n" +
            "    return -2\n" +
            "end\n" +
            "--红包未抢完 返回剩余积分\n" +
            "return score";

    /**
     * 发红包
     * 涉及分布式事务，红包服务调用user服务扣减user的积分 红包服务发生异常时user服务同时需要回滚数据
     *
     * @param redEnvlopesInput
     * @return
     * @GlobalTransactional seata分布式事务
     */
    @Override
    @GetUser
    @GlobalTransactional
    public int sendRed(RedEnvlopesInput redEnvlopesInput) {
        //获取用户id
        Integer uid = UserUtils.getUser().getId();
        RedEnvelopes redEnvelopes = QfBeanUtils.copyBean(redEnvlopesInput, RedEnvelopes.class).setUid(uid);
        //设置红包过期时间 - 24小时过期 加上一天的毫秒值
        redEnvelopes.setTimeout(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000));

        //查询用户积分 --------------------》若积分足够 扣减积分
        //这里涉及线程安全问题，只能有一个线程进入这里的两步操作 避免积分超扣
        //使用乐观锁解决 在修改用户积分时，通过业务逻辑控制积分足够被扣减 若积分不足 则不扣减 sql语句中实现
        Integer result = wxUserFeign.updateWxScore(new WxScoreUpdateInput().setUid(uid).setType(0).setScore(redEnvlopesInput.getScount())).getData();
        if (result <= 0) {
            throw new ServiceException(500, "积分不足");
        }
        //积分扣减成功 保存红包对象
        this.save(redEnvelopes);
        //红包id
        Integer redId = redEnvelopes.getId();

        //生成用户积分流水（积分流水表更新）
        wxUserFeign.createScoreDetails(new WxScoreDetails()
                .setUid(uid)
                .setBusid(redId) //业务id
                .setTarget(0)   //操作 规范点应该提取为枚举值 0-发红包 1-抢 2-充值 3-活动
                .setSourceScore(0) //原始积分 正常需要先去查询
                .setActionScore(-redEnvelopes.getScount()));

        //通过lua脚本 写入红包信息到redis
        Long flag = stringRedisTemplate.execute(new DefaultRedisScript<Long>(createRedLua, Long.class),
                Collections.singletonList(redId + ""),
                redEnvelopes.getType() + "",
                redEnvelopes.getScount() + "",
                redEnvelopes.getNumber() + "");
        if (flag != 1) {
            //红包发送未成功，抛异常 让事务回滚
            throw new ServiceException(500, "红包发送异常");
        }

        //将红包的消息发送到时间总线 通过延时队列 来回收红包  5s过期时间  red-send 需要抽离出来 用常量
        EventTemplate.sendDelay("red-send", redId, 1, TimeUnit.DAYS);

        return redId;
    }

    /**
     * 抢红包 数据库版本
     *
     * @param redId
     * @return 正数-抢到的积分 -1已抢完 -2红包过期 -3用户已抢过
     */
    @Override
    @GetUser
    @KLock("'red-' + #redId") //加分布式锁 拿到了红包还没走完抢红包流程时 阻止其他线程进行抢红包操作
    @GlobalTransactional
    public int robRed(Integer redId) {
        //获取当前抢红包的用户id
        Integer uid = UserUtils.getUser().getId();
//        Integer uid = (int) (Math.random() * 10000);//并发测试，抢红包性能
        //根据红包id 查询红包信息
        //从这里就需要开始上锁 避免其他线程对红包进行了操作，抢到积分 导致这里的实体类与数据库数据不一致
        RedEnvelopesDto redEnvelopesDto = this.queryRedById(redId);
        log.debug("[rob red] 抢红包 - {}", redEnvelopesDto);

        //判断红包状态
        if (redEnvelopesDto.getStatus() == 1) {
            //红包已抢完
            return -1;
        } else if (redEnvelopesDto.getStatus() == 2) {
            //红包已过期
            return -2;
        }
        //红包状态正常 判断用户是否已经抢过该红包 查询红包明细表 该用户的记录数
        Long count = redEnvelopesDetailsService.query().eq("redid", redId).eq("uid", uid).count();
        if (count > 0) {
            //当前红包用户已经抢过
            return -3;
        }

        //抢到的积分
        Integer robScore = 0;

        //红包状态正常 且用户可以抢该红包
        //判断剩余积分 剩余可抢人数
        if (redEnvelopesDto.getType() == 0) {
            //固定红包
            robScore = redEnvelopesDto.getHasScore() / redEnvelopesDto.getHasCount();
        } else {
            //随机红包
            if (redEnvelopesDto.getHasCount() == 1) {
                //只剩余一个红包
                robScore = redEnvelopesDto.getHasScore();
            } else {
                //红包随机算法 二倍均值法  1 ~ 积分 / 剩余人数 * 2
                //积分：1000 剩余人数：10  ->  红包范围：1~1000/10*2 = 1~200
                //积分：900 剩余人数：9    ->  红包范围：1~900/9*2 = 1~200
                robScore = (int) (Math.random() * (redEnvelopesDto.getHasScore() / redEnvelopesDto.getHasCount() * 2 - 1)) + 1;
            }
        }

        //只剩最后一个红包时 修改红包状态
        if (redEnvelopesDto.getHasCount() == 1) {
            //修改红包状态
            RedEnvelopes red = QfBeanUtils.copyBean(redEnvelopesDto, RedEnvelopes.class);
            red.setStatus(1);
            this.updateById(red);
        }

        //抢红包完成 红包明细表、积分流水表写入、修改用户积分
        RedEnvelopesDetails redEnvelopesDetails = new RedEnvelopesDetails()
                .setUid(uid)
                .setRedid(redId)
                .setType(0) //0抢红包
                .setScore(robScore);
        redEnvelopesDetailsService.save(redEnvelopesDetails);
        //修改用户积分
        wxUserFeign.updateWxScore(new WxScoreUpdateInput()
                .setScore(robScore)
                .setUid(uid)
                .setType(1));//1增加积分
        //积分流水表
        wxUserFeign.createScoreDetails(new WxScoreDetails()
                .setUid(uid)
                .setBusid(redId)
                .setSourceScore(0)
                .setActionScore(robScore)
                .setTarget(1));//1表示抢红包

        return robScore;
    }

    /**
     * 抢红包 - redis方案 监听mq队列的rob-red事件 表数据插入 红包明细，用户积分流水，用户积分修改
     *
     * @param redId
     * @param uid
     * @param score
     * @return
     */
    @Override
    @GlobalTransactional
    public int robRed(Integer redId, Integer uid, Integer score) {

        //抢红包完成 红包明细表、积分流水表写入、修改用户积分
        RedEnvelopesDetails redEnvelopesDetails = new RedEnvelopesDetails()
                .setUid(uid)
                .setRedid(redId)
                .setType(0) //0抢红包
                .setScore(score);
        redEnvelopesDetailsService.save(redEnvelopesDetails);
        //修改用户积分
        wxUserFeign.updateWxScore(new WxScoreUpdateInput()
                .setScore(score)
                .setUid(uid)
                .setType(1));//操作类型 1增加积分 0扣减积分
        //积分流水表
        wxUserFeign.createScoreDetails(new WxScoreDetails()
                .setUid(uid)
                .setBusid(redId)
                .setSourceScore(0)
                .setActionScore(score)
                .setTarget(1));//1表示抢红包

        return 1;
    }

    /**
     * 红包回退
     *
     * @param redId
     * @return
     */
    @Override
    @GlobalTransactional
    public int redBack(Integer redId) {

        //数据库方式红包回退
        //获取红包dto对象 判断红包状态 是否抢完 抢完无需操作
        //查询剩余积分 将积分返回给用户 通知用户
        //若发红包使用了 redis 上述方法不可用 数据库写入和redis抢红包是异步的
        //可能抢红包的事件还未被红包服务消费写入数据库 红包就已经到期 导致积分即被抢又被回退

        //redis版本的回退红包方法
        //1、通过lua脚本从redis中获取红包的剩余积分
        //  返回-1红包不存在
        //  返回-2红包已抢完
        //  返回其他 剩余的积分
        Long score = stringRedisTemplate.execute(new DefaultRedisScript<Long>(redBackLua, Long.class),
                Collections.singletonList(redId + ""));
        if (score == -1) {
            log.debug("[red miss] 红包回退，未找到红包 - {}", redId);
            return -1;
        }
        //红包未抢完
        if (score > 0) {
            RedEnvelopes redEnvelopes = this.getById(redId);
            //修改红包状态
            redEnvelopes.setStatus(2);//红包过期
            this.updateById(redEnvelopes);
            //生成红包明细记录
            RedEnvelopesDetails redEnvelopesDetails = new RedEnvelopesDetails()
                    .setUid(redEnvelopes.getUid())
                    .setRedid(redId)
                    .setType(1)//1-红包回退
                    .setScore(score.intValue());
            redEnvelopesDetailsService.save(redEnvelopesDetails);
            //修改发送者的积分
            wxUserFeign.updateWxScore(new WxScoreUpdateInput()
                    .setUid(redEnvelopes.getUid())
                    .setType(1)//1 增加积分
                    .setScore(score.intValue()));
            //用户积分流水表生成
            wxUserFeign.createScoreDetails(new WxScoreDetails()
                    .setUid(redEnvelopes.getUid())
                    .setBusid(redId)
                    .setSourceScore(0)
                    .setActionScore(score.intValue())
                    .setTarget(4));//4 红包回退
        }

        return 1;
    }

    /**
     * 根据红包id  查询出包含剩余积分数和红包数量的dto类
     *
     * @param redid 红包id
     * @return 红包dto类 提供给抢红包业务使用，包含剩余积分和剩余红包数属性
     */
    @Override
    public RedEnvelopesDto queryRedById(Integer redid) {
        return getBaseMapper().queryRedById(redid);
    }
}

