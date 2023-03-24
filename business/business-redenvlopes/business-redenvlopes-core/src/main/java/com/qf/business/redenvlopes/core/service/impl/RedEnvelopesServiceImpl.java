package com.qf.business.redenvlopes.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.data.redenvlopes.vo.input.RedEnvlopesInput;
import com.qf.business.redenvlopes.core.dao.RedEnvelopesDao;
import com.data.redenvlopes.entity.RedEnvelopes;
import com.qf.business.redenvlopes.core.service.RedEnvelopesService;
import com.qf.business.user.feign.WxUserFeign;
import com.qf.commons.core.exception.ServiceException;
import com.qf.commons.core.utils.QfBeanUtils;
import com.qf.commons.web.aspect.annotation.GetUser;
import com.qf.commons.web.utils.UserUtils;
import com.qf.data.user.entity.WxScoreDetails;
import com.qf.data.user.vo.input.WxScoreUpdateInput;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 红包表(RedEnvelopes)表服务实现类
 *
 * @author makejava
 * @since 2023-03-21 23:45:36
 */
@Service("redEnvelopesService")
public class RedEnvelopesServiceImpl extends ServiceImpl<RedEnvelopesDao, RedEnvelopes> implements RedEnvelopesService {

    @Autowired
    private WxUserFeign wxUserFeign;

    /**
     * 发红包
     * 涉及分布式事务，红包服务调用user服务扣减user的积分 红包服务发生异常时user服务同时需要回滚数据
     * @GlobalTransactional seata分布式事务
     * @param redEnvlopesInput
     * @return
     */
    @Override
    @GetUser
    @GlobalTransactional
    public int sendRed(RedEnvlopesInput redEnvlopesInput) {
        //获取用户id
        Integer uid = UserUtils.getUser().getId();
        RedEnvelopes redEnvelopes = QfBeanUtils.copyBean(redEnvlopesInput, RedEnvelopes.class).setUid(uid);
        //设置红包过期时间 - 24小时过期 加上一天的毫秒值
        redEnvelopes.setTimeout(new Date(System.currentTimeMillis()+24*60*60*1000));

        //查询用户积分 --------------------》若积分足够 扣减积分
        //这里涉及线程安全问题，只能有一个线程进入这里的两步操作 避免积分超扣
        //使用乐观锁解决 在修改用户积分时，通过业务逻辑控制积分足够被扣减 若积分不足 则不扣减 sql语句中实现
        Integer result = wxUserFeign.updateWxScore(new WxScoreUpdateInput().setUid(uid).setType(0).setScore(redEnvlopesInput.getScount())).getData();
        if (result<=0){
            throw new ServiceException(500,"积分不足");
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


        return redId;
    }

    /**
     * 抢红包
     *
     * @param redId
     * @return
     */
    @Override
    public int robRed(Integer redId) {
        return 0;
    }

    /**
     * 红包回退
     *
     * @param redId
     * @return
     */
    @Override
    public int redBack(Integer redId) {
        return 0;
    }
}

