package com.yao.integral.service.Impl;

import com.yao.integral.entry.domain.Integral;
import com.yao.integral.service.IntegralService;
import org.springframework.stereotype.Service;

/**
 * @author pengjie_yao
 * @date 2020/1/2 17:05
 */
@Service
public class IntegralServiceImpl implements IntegralService {

    /**
     * 新增积分
     *
     * @param integral
     */
    @Override
    public void add(Integral integral) {
        System.out.println("新增积分信息为：" + integral.toString());
    }


    /**
     * 消费积分
     *
     * @param userId
     * @param amount
     * @return
     */
    @Override
    public String consume(String userId, Integer amount) {
        System.out.println("数据库根据userId为：" + userId + "获取总积分金额为：100");
        System.out.println("消费了" + amount + "积分");
        System.out.println("剩余积分数为：" + (100 - amount));
        return (100 - amount)+"";
    }
}
