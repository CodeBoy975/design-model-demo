package com.yao.order.controller;

import com.yao.order.entry.domain.Order;
import com.yao.order.entry.vo.OrderVO;
import com.yao.order.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *  订单系统
 * @author pengjie_yao
 * @date 2020/1/2 16:53
 */
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     *  下订单
     * @param orderVO
     */
    @PostMapping("/order/add")
    public void add(@RequestBody OrderVO orderVO) {
        Order order = new Order();
         BeanUtils.copyProperties(orderVO, order);
        orderService.add(order);
        return;
    }
}
