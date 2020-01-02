package com.yao.integral.service;

import com.yao.integral.entry.domain.Integral;

/**
 * @author pengjie_yao
 * @date 2020/1/2 17:04
 */

public interface IntegralService {

    /**
     * 新增积分
     * @param integral
     */
    void add(Integral integral);

    /**
     *
     *  消费积分
     * @param userId
     * @param amount
     * @return
     */
    String consume(String userId, Integer amount);
}
