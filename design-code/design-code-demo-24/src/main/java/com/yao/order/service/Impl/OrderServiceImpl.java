package com.yao.order.service.Impl;

import com.yao.integral.entry.domain.Integral;
import com.yao.integral.service.IntegralService;
import com.yao.order.entry.domain.Order;
import com.yao.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author pengjie_yao
 * @date 2020/1/2 16:58
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private IntegralService integralService;

    /**
     * 下订单
     * @param order
     */
    @Override
    public void add(Order order) {

        // 执行订单表的数据库操作
        System.out.println("数据库插入订单信息为："+order.toString());
        // 获取订单金额
        Integer amount = order.getAmount();
        // 计算订单金额的10%
        Integer credit = amount / 10;
        // 调用积分系统进行添加该订单对应的积分信息
        Integral integral = new Integral();
        integral.setCredit(credit);
        integral.setEventId(order.getId());
        integral.setChannelType("order");
        integral.setExpiredTime(new Date());
        integralService.add(integral);
    }
}
