package com.qf.business.user.feign;

import com.qf.commons.data.result.R;
import com.qf.data.user.entity.WxScoreDetails;
import com.qf.data.user.entity.WxUser;
import com.qf.data.user.vo.input.WxScoreUpdateInput;
import com.qf.data.user.vo.input.WxUserInput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * author 14526
 * create_time 2023/3/17
 */
@FeignClient(value = "user-server", contextId = "wxUser")
@RequestMapping("/wx")
public interface WxUserFeign {

    @RequestMapping("/query")
    R<WxUser> queryWxUser(@RequestBody WxUserInput wxUserInput); //对象参数需要加requestBody注解 控制层也需要加

    /**
     * 积分修改
     * @return
     */
    @RequestMapping("/score/sub")
    R<Integer> updateWxScore(@RequestBody WxScoreUpdateInput wxScoreUpdateInput);

    /**
     * 生成积分流水(积分流水表)
     * @return
     */
    @RequestMapping("/score/details")
    R<Integer> createScoreDetails(@RequestBody WxScoreDetails wxScoreDetails);
}
