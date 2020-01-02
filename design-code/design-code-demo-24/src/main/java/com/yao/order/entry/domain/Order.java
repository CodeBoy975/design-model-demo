package com.yao.order.entry.domain;

import lombok.Data;

/**
 *  订单实体类
 * @author pengjie_yao
 * @date 2020/1/2 16:54
 */
@Data
public class Order {

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
