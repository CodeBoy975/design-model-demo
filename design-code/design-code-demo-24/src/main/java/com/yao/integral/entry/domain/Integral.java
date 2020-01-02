package com.yao.integral.entry.domain;

import lombok.Data;

import java.util.Date;

/**
 *  积分实体类
 * @author pengjie_yao
 * @date 2020/1/2 16:46
 */
@Data
public class Integral {

    /**
     *  id
     */
    private String id;

    /**
     * 赚取/消费渠道类型，这里管理渠道表，根据该id可以获取对应渠道数据
     * 这里可以设计为枚举，比如当channelType=order，则表示积分是属于订单来源，则对应的eventId，则是订单表的id
     */
    private String channelType;

    /**
     * 相关事件id:比如订单id，评论id，优惠券交易id
     */
    private String eventId;

    /**
     * 积分(赚取为正值、消费为负值)
     */
    private Integer credit;

    /**
     *  积分过期事件
     */
    private Date expiredTime;
}
