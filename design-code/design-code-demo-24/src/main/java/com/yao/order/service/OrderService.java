package com.yao.order.service;

import com.yao.order.entry.domain.Order;

/**
 * @author pengjie_yao
 * @date 2020/1/2 16:58
 */
public interface OrderService {

    /**
     *  下订单
     * @param order
     */
    void add(Order order);
}
