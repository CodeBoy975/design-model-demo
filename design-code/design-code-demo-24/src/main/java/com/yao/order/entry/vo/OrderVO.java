package com.yao.order.entry.vo;

import lombok.Data;

/**
 * @author pengjie_yao
 * @date 2020/1/2 16:57
 */
@Data
public class OrderVO {

    /**
     * 订单id
     */
    private String id;

    /**
     * 金额
     */
    private Integer amount;

    /**
     * 用户id
     */
    private String userId;
}
